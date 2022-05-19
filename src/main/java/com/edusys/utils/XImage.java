/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import java.nio.file.Files;

/**
 *
 * @author dell
 */
public class XImage {
    public static Image getAppIcon(){
        URL url = XImage.class.getResource("/com/edusys/hinh/fpt.png");
        return new javax.swing.ImageIcon(url).getImage();
    }
    public static void save(File src){
        File dst = new File("Logos",src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getCanonicalPath());
            Files.copy(from,to,java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public static ImageIcon read(String fileName){
        File path = new java.io.File("logos",fileName);
        return new javax.swing.ImageIcon(path.getAbsolutePath());
    }
}