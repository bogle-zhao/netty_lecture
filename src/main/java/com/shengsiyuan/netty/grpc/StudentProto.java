// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package com.shengsiyuan.netty.grpc;

public final class StudentProto {
  private StudentProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_shengsiyuan_netty_grpc_MyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_shengsiyuan_netty_grpc_MyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_shengsiyuan_netty_grpc_MyResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_shengsiyuan_netty_grpc_MyResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_shengsiyuan_netty_grpc_StudentRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_shengsiyuan_netty_grpc_StudentRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_shengsiyuan_netty_grpc_StudentResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_shengsiyuan_netty_grpc_StudentResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_shengsiyuan_netty_grpc_StudentResponseList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_shengsiyuan_netty_grpc_StudentResponseList_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\rStudent.proto\022\032com.shengsiyuan.netty.g" +
      "rpc\"\035\n\tMyRequest\022\020\n\010username\030\001 \001(\t\"\036\n\nMy" +
      "Response\022\020\n\010realname\030\002 \001(\t\"\035\n\016StudentReq" +
      "uest\022\013\n\003age\030\001 \001(\005\":\n\017StudentResponse\022\014\n\004" +
      "name\030\001 \001(\t\022\013\n\003age\030\002 \001(\005\022\014\n\004city\030\003 \001(\t\"[\n" +
      "\023StudentResponseList\022D\n\017studentResponse\030" +
      "\001 \003(\0132+.com.shengsiyuan.netty.grpc.Stude" +
      "ntResponse2\350\002\n\016StudentService\022h\n\025GetReal" +
      "NameByUsername\022%.com.shengsiyuan.netty.g" +
      "rpc.MyRequest\032&.com.shengsiyuan.netty.gr" +
      "pc.MyResponse\"\000\022o\n\020GetStudentsByAge\022*.co" +
      "m.shengsiyuan.netty.grpc.StudentRequest\032" +
      "+.com.shengsiyuan.netty.grpc.StudentResp" +
      "onse\"\0000\001\022{\n\030GetStudentsWrapperByAges\022*.c" +
      "om.shengsiyuan.netty.grpc.StudentRequest" +
      "\032/.com.shengsiyuan.netty.grpc.StudentRes" +
      "ponseList\"\000(\001B,\n\032com.shengsiyuan.netty.g" +
      "rpcB\014StudentProtoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_shengsiyuan_netty_grpc_MyRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_shengsiyuan_netty_grpc_MyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_shengsiyuan_netty_grpc_MyRequest_descriptor,
        new String[] { "Username", });
    internal_static_com_shengsiyuan_netty_grpc_MyResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_shengsiyuan_netty_grpc_MyResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_shengsiyuan_netty_grpc_MyResponse_descriptor,
        new String[] { "Realname", });
    internal_static_com_shengsiyuan_netty_grpc_StudentRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_shengsiyuan_netty_grpc_StudentRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_shengsiyuan_netty_grpc_StudentRequest_descriptor,
        new String[] { "Age", });
    internal_static_com_shengsiyuan_netty_grpc_StudentResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_com_shengsiyuan_netty_grpc_StudentResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_shengsiyuan_netty_grpc_StudentResponse_descriptor,
        new String[] { "Name", "Age", "City", });
    internal_static_com_shengsiyuan_netty_grpc_StudentResponseList_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_com_shengsiyuan_netty_grpc_StudentResponseList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_shengsiyuan_netty_grpc_StudentResponseList_descriptor,
        new String[] { "StudentResponse", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
