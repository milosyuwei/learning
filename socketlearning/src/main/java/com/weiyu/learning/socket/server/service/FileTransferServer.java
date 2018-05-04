package com.weiyu.learning.socket.server.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;

@Component
@Slf4j
public class FileTransferServer implements InitializingBean {
    org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor poolTaskExecutor;
    private static final int SERVER_PORT = 20180;
    private static DecimalFormat df = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        try {
            df = new DecimalFormat("#0.0");
            poolTaskExecutor = new ThreadPoolTaskExecutor();
            poolTaskExecutor.setCorePoolSize(1);
            poolTaskExecutor.setKeepAliveSeconds(10);
            poolTaskExecutor.setMaxPoolSize(100);
            poolTaskExecutor.setQueueCapacity(100);
            poolTaskExecutor.initialize();

            while (true) {
                Socket socket = serverSocket.accept();

                poolTaskExecutor.submit(new SocketProcessThread(socket));
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }

    class SocketProcessThread extends Thread {
        Socket socket;

        public SocketProcessThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (DataInputStream inputStream = new DataInputStream(socket.getInputStream())) {
                String fileName = inputStream.readUTF();
                long fileLength = inputStream.readLong();
                File directory = FileUtils.getFile("c:\\tmp");
                if(!directory.exists()){
                    directory.mkdir();
                }
                File file;
                int tryCount = 0;
                String tmpFileName = fileName;
                do {
                    file = FileUtils.getFile(directory.getAbsolutePath() + File.separatorChar + fileName);
                    if(fileName.contains(".")){
                        int dotIndex = tmpFileName.lastIndexOf(".");
                        fileName = tmpFileName.substring(0,dotIndex)
                                + tryCount
                                + tmpFileName.substring(dotIndex);
                    }else {
                        fileName = tmpFileName + tryCount;
                    }

                }while (file.exists());
                FileUtils.copyToFile(inputStream,file);
            } catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }
}
