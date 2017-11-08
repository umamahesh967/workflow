package com.stackroute.workflowengineservice.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.lib.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stackroute.workflowengineservice.exception.FileGenerationException;
import com.stackroute.workflowengineservice.exception.InternalUnixCommandException;
import com.stackroute.workflowengineservice.exception.JgitInternalException;

@Service
public class WorkflowService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String build;
	private String test;
	private String run;
	private String compile;
	private HashMap<String, String> cmd_value = new HashMap<String, String>();
	
	public void init_commands(String bld,String tst,String rn, String comp) {
		 build = bld;
		 test = tst;
		 run = rn;
		 compile =comp;
		 cmd_value.put("build", bld);
		 cmd_value.put("test", tst);
		 cmd_value.put("run", rn);
		 cmd_value.put("compile", comp);
	}
	
	public String addStage(String stage) {
		String ret = ""
				+ "   	stage('" +stage +"'){\n"
				+ "			steps {\n"
				+ "				sh '"+ cmd_value.get(stage) +"' \n"
				+ "			}\n"
				+ "		}\n"
				+ "\n";
		return ret;
	}
	
	public void createJenkinsFile(File jenkinsFile_path_new, HashMap<String,String> cmds) throws FileGenerationException {
		BufferedWriter writer = null;
		FileWriter fw = null;
		
		logger.debug("build value : " + build );
		logger.debug("run value : " + run );
		logger.debug("compile value : " + compile );
			try {
	
				fw = new FileWriter(jenkinsFile_path_new);
				writer = new BufferedWriter(fw);
				 StringBuilder sb = new StringBuilder();
		          writer.append("pipeline {\n");
		          writer.append("	agent any\n");
		          
		          Iterator it = cmd_value.entrySet().iterator();
		          while(it.hasNext()) {
		        	  Map.Entry pair = (Map.Entry)it.next();
		        	  logger.debug("writing : " + pair.getKey() + " : " + pair.getValue());
		        	  writer.append(addStage(pair.getKey().toString()));
		        	  it.remove();
		          }
		          writer.append("}\n");
		    	
				logger.debug("Done creating jen file");
	
			} catch (IOException e) {
//				e.printStackTrace();
				logger.debug("Unable to generate files and folder.");
				throw new FileGenerationException(e.getMessage());
			} finally {
				try {
					if (writer != null)
						{writer.close();}
					if (fw != null)
						{fw.close();}
				} catch (IOException ex) {
					ex.printStackTrace();
	
				}
			}
	}
	
	/*
	 * create the file on the given path.handles all the exceptions as well.
	 * */
	public void createFile(File path) throws FileGenerationException {
		/* create the dir first */

		String workingDir = System.getProperty("user.dir");
		 logger.debug("Current working directory : " + workingDir);
		// if the directory does not exist, create it
		if (!path.exists()) {
		    logger.debug("creating directory: " + path.getName());
		    boolean result = false;

		    try{
				if (!path.getParentFile().exists()) {
					path.getParentFile().mkdirs();
				}
					
				if (!path.exists()) {
					path.createNewFile();
				}
				path.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    	throw new FileGenerationException(se.getMessage());
		    } 
		    catch(IOException e) {
		    	throw new FileGenerationException(e.getMessage());
		    }
		    if(result) {    
		        logger.debug("DIR created");  
		    }
		}
	}
	
	/*
	 * copying the jenkinfile from the JenkinsFolder to the cloned_repo 
	 * folder.
	 * 
	 * */
	public void copyJenkinsfileToRepo(File JenkinsfileInRepo, File jenkinsfilePath) throws FileGenerationException {
		/*
		try {
			
		    FileUtils.copyFile(jenkinsfile_path, Jenkinsfile_in_repo);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		*/
	    FileInputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(jenkinsfilePath);
	        os = new FileOutputStream(JenkinsfileInRepo);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    }catch(IOException e) {
	    	throw new FileGenerationException(e.getMessage());
	    } finally {
	    	try {
	    		 is.close();
	 	        os.close();
	    	}
	    	catch(IOException e) {
	    		throw new FileGenerationException(e.getMessage());
	    	}
	       
	    }
	}
	
	/*
	 * Run the given `cmd` unix command.
	 * 
	 * */
	public boolean runUnixCommand(String cmd) throws InternalUnixCommandException {
        try {
//            String target = new String("./test.sh");
            //String target = new String("mkdir stackOver");
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd);
            proc.waitFor();
            StringBuffer output = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = "";                       
            while ((line = reader.readLine())!= null) {
                    output.append(line + "\n");
            }
            logger.debug("### " + output);
            return true;
	    } catch (Throwable t) {
	            t.printStackTrace();
	       
	            throw new InternalUnixCommandException(t.getMessage());
	    }
	}
	
	
    // replace to service
    public void deleteFolder(File folder_path) throws InternalUnixCommandException {
		if (folder_path.exists()) {
			runUnixCommand("rm -rf " + folder_path);
		}
    }
    
}
