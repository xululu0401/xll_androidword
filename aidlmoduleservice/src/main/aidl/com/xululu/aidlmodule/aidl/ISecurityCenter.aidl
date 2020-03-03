package com.xululu.aidlmodule.aidl;


interface ISecurityCenter {

    String encrypt(String content);
    String decrypt(String password);
}