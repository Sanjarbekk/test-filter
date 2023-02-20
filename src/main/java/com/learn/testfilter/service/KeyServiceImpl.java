package com.learn.testfilter.service;

import org.springframework.stereotype.Service;

@Service
public class KeyServiceImpl implements KeyService{
    @Override
    public String getById(String id) {
        return "2";
    }
}
