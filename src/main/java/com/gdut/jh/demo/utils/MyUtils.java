package com.gdut.jh.demo.utils;

import com.gdut.jh.demo.pojo.entity.Article;
import com.sun.xml.bind.v2.schemagen.xmlschema.Particle;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MyUtils {
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String removeHtml(String html) {
        String content = "";
        content = html.replaceAll( ".*?<body.*?>(.*?)<\\/body>", "$1");
        content=content.replaceAll("</?[a-zA-Z]+[^><]*>","");
        content.replaceAll("\n","");
        return content;
    }

    public static int compare(String s1,String s2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf.parse(s1);
            Date date2 = sdf.parse(s2);
            if(date1.before(date2)) return 1;
            else return -1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String toFile(String str,String pos){
        ByteArrayInputStream is = new ByteArrayInputStream(str.getBytes());
        OutputStream os = null;
        String folder = "F:/Project/IDEAproject/Static/"+pos;
        File f = new File(folder);
        File file = new File(f,getRandomString(6));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdir();

        try {
            os = new FileOutputStream(file);
            int len = 0;
            byte[] buffer = new byte[8192];

            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return folder+"/"+file.getName();
    }

    public static String toString(String pos){
        File file = new File(pos);
        try {
            InputStream is = new FileInputStream(file);
            StringBuffer sb = null;
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(is));

                sb = new StringBuffer();

                String data;
                while ((data = br.readLine()) != null) {
                    sb.append(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Article articleTran(Article article){
        article.setHtml(MyUtils.toString(article.getHtml()));
        article.setMd(MyUtils.toString(article.getMd()));
        return article;
    }

}
