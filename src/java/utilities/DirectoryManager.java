/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.File;

/**
 * Handles creating of the file structure for the Charity, only works with Linux
 * files paths right now, not Windows.
 * 
 * @author Kevin Murphy
 */
public class DirectoryManager {
    
    /* Mechinism for debugging */
    private static final boolean DEBUG_ON = true;
    /* the charity's name, use for crearing the directory */
    private static String charityName; 
    
    /*
     * Constructor 
     * 
     * @param charityName  The name of the charity, which will be used as the 
     * name for the directory which will house all content created by that charity
     */
    public DirectoryManager(String charityName){
        /* Converts to lower case, trims leading and following spaces, and removes whitespace characters */
        this.charityName = charityName.toLowerCase().trim().replaceAll("\\s+","");
    }
    
    /**
     *  Creates the directory structure containing; a JSON directory to hold all
     *  JSON files generated by the charity, and an uploads folder to house all
     *  charity upload files
     * 
     * @param path Path where the file structure should be created in
     */
    public void createDirStructure(String path){
        
     
        /* Creates the directory path */
        String charityDirPath = path + "/charities/" + charityName;
        if(DEBUG_ON){
            System.out.println("charityDirPath: " + charityDirPath);
        }
        
        String charityJSONDirPath    = charityDirPath + "/json";
        String charityUploadsDirPath = charityDirPath + "/uploads";
        
        /* Creates the new directories */
        //specific charity
        File charityDir = new File(charityDirPath);
        charityDir.mkdirs();
        
        //thier json files
        File jsonDir = new File(charityJSONDirPath);
        jsonDir.mkdirs();
        
        //their uploads
        File uploadsDir = new File(charityUploadsDirPath);
        uploadsDir.mkdirs();
        
        if(DEBUG_ON){
            System.out.println("Create Path: " + charityDir);
        }
        
    }
    
}
