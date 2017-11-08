package com.stackroute.workflowengineservice.service;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stackroute.workflowengineservice.exception.JgitInternalException;

/*

 clone : 
 
 cd cloned_repo
 
 svn co --username your_name https://svn.server.com/repository/trunk .

and the password should be prompted afterwards.

Note : To specify current directory, use a "." for your destination
 directory:

However, it's not necessary to specify the username, svn 
is going to prompt you anyway.

 * */
public class SvnVersionControlService extends VersionControlService{
// TODO: override the methods
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Git cloning_repo(String repo_url, File cloning_path) throws JgitInternalException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * svn commit -m "added howto section."
	 */
	@Override
	public void git_commit(Git git, String msg) throws NoHeadException, NoMessageException, UnmergedPathsException,
			ConcurrentRefUpdateException, WrongRepositoryStateException, GitAPIException {
		// TODO Auto-generated method stub
		
	}

}
