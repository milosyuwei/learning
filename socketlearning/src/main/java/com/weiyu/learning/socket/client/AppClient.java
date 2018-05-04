package com.weiyu.learning.socket.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

@Slf4j
public class AppClient {
    private static final int SERVER_PORT = 20180;
    private static final String SERVER_RUL = "";

    public static void main(String[] args) throws Exception{
        Socket client = new Socket(SERVER_RUL,SERVER_PORT);
        FileInputStream fis=null;
        DataOutputStream dos = null;
        try{
            File file = FileUtils.getFile("D:\\民办南模.jpg");
            if(file.exists()){
                fis = new FileInputStream(file);
                dos = new DataOutputStream(client.getOutputStream());

                //file name , file length
                dos.writeUTF(file.getName());
                dos.flush();
                dos.writeLong(file.length());
                dos.flush();

                //start translate
                byte[] bytes = new byte[1024];
                int length = 0;
                long process = 0;
                boolean needPrint = true;
                long baseLine =0;
                while ((length = fis.read(bytes,0,bytes.length)) != -1){
                    dos.write(bytes,0,length);
                    dos.flush();
                }
            }

        }catch (IOException e){
            log.error(ExceptionUtils.getStackTrace(e));
        }finally {
            if(fis!=null){
                fis.close();
            }
            if(dos!=null){
                dos.close();
            }
            client.close();
        }

    }
}
