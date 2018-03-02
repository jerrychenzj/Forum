package com.jin.service.impl;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JINS on 2018/3/1.
 */
@Service
public class SensitiveService implements InitializingBean{
    private static final Logger logger = LoggerFactory.getLogger(SensitiveService.class);
    private class TrieNode{
        private boolean end = false;
        private Map<Character,TrieNode> subNodes = new HashMap<>();

        public void addSubNode(Character c,TrieNode node){
            subNodes.put(c,node);
        }

        public TrieNode getSubNode(Character key){
            return subNodes.get(key);
        }

        public boolean isKeyWordEnd(){
            return end;
        }

        public void setKeyWordEnd(boolean end){
            this.end = end;
        }
    }

    private  TrieNode root = new TrieNode();

    @Override
    public void afterPropertiesSet() throws Exception {
        try{
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Sensitive.txt");
            InputStreamReader reader  = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while((line=bufferedReader.readLine())!=null)
            {
                addKeyWord(line);
            }
            inputStream.close();
            reader.close();
            bufferedReader.close();
        }catch (Exception e){
            System.out.println("读取敏感词文件失败！");
            logger.error("读取敏感词文件失败！");
        }
    }
//增加关键词
    private void addKeyWord(String lineTex){
        lineTex = lineTex.trim();
        TrieNode tempTriNode = root;
        for(int i = 0;i<lineTex.length();i++){
            Character c = lineTex.charAt(i);
            TrieNode node = tempTriNode.getSubNode(c);
            if(node == null)
            {
                node = new TrieNode();
                tempTriNode.addSubNode(c,node);
            }
            tempTriNode = node;
            if(i == lineTex.length()-1)
            {
                tempTriNode.setKeyWordEnd(true);
            }
        }
    }
    //过滤操作
    public String filter(String text){
        if(StringUtils.isBlank(text))
        {
            return text;
        }

        String replacement = "***";
        StringBuilder sb = new StringBuilder();

        TrieNode tempTrieNode = root;
        int begin = 0;
        int position = 0;

        while(position < text.length()){
            Character c = text.charAt(position);

            if(isSymbol(c)){
                if(tempTrieNode == root)
                {
                    sb.append(c);
                    begin++;
                }
                position++;
                continue;
            }


            TrieNode node = tempTrieNode.getSubNode(c);
            if(node == null)
            {
                 sb.append(text.charAt(begin));
                 begin +=1;
                 position = begin;
                 tempTrieNode = root;
            }else if(node.isKeyWordEnd()){
                sb.append(replacement);
                position +=1;
                begin = position;
                tempTrieNode = root;
            }else {
                position++;
                tempTrieNode = node;
            }

        }
        sb.append(text.substring(begin));
        return sb.toString();
    }
   //过滤不相干字符
    private boolean isSymbol(char c){
        int num =(int) c;
        //东亚文字  0x2E80~0x9FFF
        return !CharUtils.isAsciiAlphanumeric(c)&&(num < 0x2E80||num > 0x9FFF);
    }

}
