package com.demo.client;

import com.demo.msg.PersonBean;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String args[]) throws IOException {
        Socket socket = new Socket("localhost", 8082);

        //方式一
        /*PersonBean.Person person = PersonBean.Person.newBuilder().
                setName("ZhangSan").setAge(20).setGender("男").build();*/

        //方式二
        PersonBean.Person.Builder builder = PersonBean.Person.newBuilder();
        builder.setName("张三");
        builder.setAge(20);
        builder.setGender("男");
        PersonBean.Person.PhoneNumber.Builder buildPhoneNumber = PersonBean.
                Person.PhoneNumber.newBuilder();
        builder.addPhones(buildPhoneNumber.setNumber("135-9044").
                setType(PersonBean.Person.PhoneType.MOBILE));
        builder.addPhones(buildPhoneNumber.setNumber("172-9048").
                setType(PersonBean.Person.PhoneType.HOME));

        PersonBean.Person person = builder.build();


        PersonBean.AddressBook.Builder builderAdd = PersonBean.AddressBook.newBuilder();
        builderAdd.addPeople(person);
        builderAdd.addPeople(person);
        PersonBean.AddressBook addressBook = builderAdd.build();



        OutputStream os = socket.getOutputStream();
        os.write(addressBook.toByteArray());
        os.flush();
        os.close();
        System.out.println("client send person");
    }
}
