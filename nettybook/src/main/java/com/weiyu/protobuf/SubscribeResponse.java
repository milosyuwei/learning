// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SubscribeReq.proto

package com.weiyu.protobuf;

/**
 * Protobuf type {@code weiyu.SubscribeResponse}
 */
public  final class SubscribeResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:weiyu.SubscribeResponse)
    SubscribeResponseOrBuilder {
  // Use SubscribeResponse.newBuilder() to construct.
  private SubscribeResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SubscribeResponse() {
    subReqID_ = 0;
    responseCode_ = 0;
    desc_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private SubscribeResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            subReqID_ = input.readInt32();
            break;
          }
          case 16: {

            responseCode_ = input.readInt32();
            break;
          }
          case 26: {
            String s = input.readStringRequireUtf8();

            desc_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return SubscribeProto.internal_static_weiyu_SubscribeResponse_descriptor;
  }

  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return SubscribeProto.internal_static_weiyu_SubscribeResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            SubscribeResponse.class, Builder.class);
  }

  public static final int SUBREQID_FIELD_NUMBER = 1;
  private int subReqID_;
  /**
   * <code>optional int32 subReqID = 1;</code>
   */
  public int getSubReqID() {
    return subReqID_;
  }

  public static final int RESPONSECODE_FIELD_NUMBER = 2;
  private int responseCode_;
  /**
   * <code>optional int32 responseCode = 2;</code>
   */
  public int getResponseCode() {
    return responseCode_;
  }

  public static final int DESC_FIELD_NUMBER = 3;
  private volatile Object desc_;
  /**
   * <code>optional string desc = 3;</code>
   */
  public String getDesc() {
    Object ref = desc_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      desc_ = s;
      return s;
    }
  }
  /**
   * <code>optional string desc = 3;</code>
   */
  public com.google.protobuf.ByteString
      getDescBytes() {
    Object ref = desc_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      desc_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (subReqID_ != 0) {
      output.writeInt32(1, subReqID_);
    }
    if (responseCode_ != 0) {
      output.writeInt32(2, responseCode_);
    }
    if (!getDescBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, desc_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (subReqID_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, subReqID_);
    }
    if (responseCode_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, responseCode_);
    }
    if (!getDescBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, desc_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof SubscribeResponse)) {
      return super.equals(obj);
    }
    SubscribeResponse other = (SubscribeResponse) obj;

    boolean result = true;
    result = result && (getSubReqID()
        == other.getSubReqID());
    result = result && (getResponseCode()
        == other.getResponseCode());
    result = result && getDesc()
        .equals(other.getDesc());
    return result;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + SUBREQID_FIELD_NUMBER;
    hash = (53 * hash) + getSubReqID();
    hash = (37 * hash) + RESPONSECODE_FIELD_NUMBER;
    hash = (53 * hash) + getResponseCode();
    hash = (37 * hash) + DESC_FIELD_NUMBER;
    hash = (53 * hash) + getDesc().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static SubscribeResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static SubscribeResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static SubscribeResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static SubscribeResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static SubscribeResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static SubscribeResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static SubscribeResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static SubscribeResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static SubscribeResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static SubscribeResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(SubscribeResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code weiyu.SubscribeResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:weiyu.SubscribeResponse)
      SubscribeResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return SubscribeProto.internal_static_weiyu_SubscribeResponse_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return SubscribeProto.internal_static_weiyu_SubscribeResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              SubscribeResponse.class, Builder.class);
    }

    // Construct using com.weiyu.protobuf.SubscribeResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      subReqID_ = 0;

      responseCode_ = 0;

      desc_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return SubscribeProto.internal_static_weiyu_SubscribeResponse_descriptor;
    }

    public SubscribeResponse getDefaultInstanceForType() {
      return SubscribeResponse.getDefaultInstance();
    }

    public SubscribeResponse build() {
      SubscribeResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public SubscribeResponse buildPartial() {
      SubscribeResponse result = new SubscribeResponse(this);
      result.subReqID_ = subReqID_;
      result.responseCode_ = responseCode_;
      result.desc_ = desc_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof SubscribeResponse) {
        return mergeFrom((SubscribeResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(SubscribeResponse other) {
      if (other == SubscribeResponse.getDefaultInstance()) return this;
      if (other.getSubReqID() != 0) {
        setSubReqID(other.getSubReqID());
      }
      if (other.getResponseCode() != 0) {
        setResponseCode(other.getResponseCode());
      }
      if (!other.getDesc().isEmpty()) {
        desc_ = other.desc_;
        onChanged();
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      SubscribeResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (SubscribeResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int subReqID_ ;
    /**
     * <code>optional int32 subReqID = 1;</code>
     */
    public int getSubReqID() {
      return subReqID_;
    }
    /**
     * <code>optional int32 subReqID = 1;</code>
     */
    public Builder setSubReqID(int value) {
      
      subReqID_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 subReqID = 1;</code>
     */
    public Builder clearSubReqID() {
      
      subReqID_ = 0;
      onChanged();
      return this;
    }

    private int responseCode_ ;
    /**
     * <code>optional int32 responseCode = 2;</code>
     */
    public int getResponseCode() {
      return responseCode_;
    }
    /**
     * <code>optional int32 responseCode = 2;</code>
     */
    public Builder setResponseCode(int value) {
      
      responseCode_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 responseCode = 2;</code>
     */
    public Builder clearResponseCode() {
      
      responseCode_ = 0;
      onChanged();
      return this;
    }

    private Object desc_ = "";
    /**
     * <code>optional string desc = 3;</code>
     */
    public String getDesc() {
      Object ref = desc_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        desc_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>optional string desc = 3;</code>
     */
    public com.google.protobuf.ByteString
        getDescBytes() {
      Object ref = desc_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        desc_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string desc = 3;</code>
     */
    public Builder setDesc(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      desc_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string desc = 3;</code>
     */
    public Builder clearDesc() {
      
      desc_ = getDefaultInstance().getDesc();
      onChanged();
      return this;
    }
    /**
     * <code>optional string desc = 3;</code>
     */
    public Builder setDescBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      desc_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:weiyu.SubscribeResponse)
  }

  // @@protoc_insertion_point(class_scope:weiyu.SubscribeResponse)
  private static final SubscribeResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new SubscribeResponse();
  }

  public static SubscribeResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SubscribeResponse>
      PARSER = new com.google.protobuf.AbstractParser<SubscribeResponse>() {
    public SubscribeResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SubscribeResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SubscribeResponse> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<SubscribeResponse> getParserForType() {
    return PARSER;
  }

  public SubscribeResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

