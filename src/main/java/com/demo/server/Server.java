package com.demo.server;

import com.demo.msg.PersonBean;
import com.google.protobuf.ByteString;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String args[]) throws IOException {
        ServerSocket ss = new ServerSocket(8082);
        System.out.println("服务开始");
        Socket socket = ss.accept();
        System.out.println("a client connected");
        /*PersonBean.Person person = PersonBean.Person.
                parseFrom(ByteString.readFrom(socket.getInputStream()));
        if(person != null){
            System.out.println("name: "+person.getName());
            System.out.println("age: "+person.getAge());
            System.out.println("gender: "+person.getGender());
            System.out.println("phoneType: "+person.getPhones(0));
            System.out.println("phoneType: "+person.getPhones(1));
            //注意使用toString中文会乱码
//            System.out.println("server received data: \n"+person.toString());
        }*/

        PersonBean.AddressBook addressBook = PersonBean.
                AddressBook.parseFrom(ByteString.readFrom(socket.getInputStream()));
        if(addressBook != null){
            System.out.println("addressBook: "+addressBook.toString());
        }
    }
}
