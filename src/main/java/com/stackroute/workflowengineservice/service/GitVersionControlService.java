package com.stackroute.workflowengineservice.service;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;

import com.stackroute.workflowengineservice.exception.JgitInternalException;

public class GitVersionControlService extends VersionControlService{

	public Git cloning_repo(String repo_url, File cloning_path) throws JgitInternalException {
    	
        Git git;
        System.out.println("clongin started via cloning_repo..");
		try {
			git = Git.cloneRepository()
					  .setURI( repo_url )
					  .setDirectory( cloning_path )
					  .setCloneAllBranches( true )
					  .call();
			System.out.println("clongin done via cloning_repo..");
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new JgitInternalException(e.getMessage());
			
		}
		return git;
    }
	public void git_commit(Git git, String msg) throws NoHeadException, NoMessageException, UnmergedPathsException, ConcurrentRefUpdateException, WrongRepositoryStateException, GitAPIException {
		// TODO Auto-generated method stub
		git.commit().setMessage(msg ).call();
	}
	    
    /*
     * 
     * pushing using jgit : https://github.com/eclipse/jgit/blob/master/org.eclipse.jgit/src/org/eclipse/jgit/api/PushCommand.java
     *
    public void add_in_git() {
    	try (Git git = new Git(repository)) {
            // create the file
            File myfile = new File(repository.getDirectory().getParent(), "testfile");
            if(!myfile.createNewFile()) {
                throw new IOException("Could not create file " + myfile);
            }

            // Stage all files in the repo including new files
            git.add().addFilepattern(".").call();

           
        }
    }
    
    public void commit_in_git() {
    	try (Git git = new Git(repository)) {
	    	// and then commit the changes.
	        git.commit()
	                .setMessage("Commit jenkinsfile")
	                .call();
	
	        // Stage all changed files, omitting new files, and commit with one command
	        git.commit()
	                .setAll(true)
	                .setMessage("Commit changes to all files")
	                .call();
	
	
	        System.out.println("Committed all changes to repository at " + repository.getDirectory());
    
    	}
    }
    
    public void open_gir_repo() {
        // now open the resulting repository with a FileRepositoryBuilder
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        try (Repository repository = builder.setGitDir(repoDir)
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build()) {
            System.out.println("Having repository: " + repository.getDirectory());

            // the Ref holds an ObjectId for any type of object (tree, commit, blob, tree)
            Ref head = repository.exactRef("refs/heads/master");
            System.out.println("Ref of refs/heads/master: " + head);
        }
    }
    
    public static Repository createNewRepository() throws IOException {
        // prepare a new folder
        File localPath = File.createTempFile("TestGitRepository", "");
        if(!localPath.delete()) {
            throw new IOException("Could not delete temporary file " + localPath);
        }

        // create the directory
        Repository repository = FileRepositoryBuilder.create(new File(localPath, ".git"));
        repository.create();

        return repository;
    }
    
    private static File createSampleGitRepo() throws IOException, GitAPIException {
        try (Repository repository = createNewRepository()) {
            System.out.println("Temporary repository at " + repository.getDirectory());

            // create the file
            File myfile = new File(repository.getDirectory().getParent(), "testfile");
            if(!myfile.createNewFile()) {
                throw new IOException("Could not create file " + myfile);
            }

            // run the add-call
            try (Git git = new Git(repository)) {
                git.add()
                        .addFilepattern("testfile")
                        .call();


                // and then commit the changes
                git.commit()
                        .setMessage("Added testfile")
                        .call();
            }

            System.out.println("Added file " + myfile + " to repository at " + repository.getDirectory());

            return repository.getDirectory();
        }
    }
    */
}
