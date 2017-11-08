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

public abstract class VersionControlService {


	//TODO: exception must be common custom exception for git and svn
    public abstract Git cloning_repo(String repo_url, File cloning_path) throws JgitInternalException;
	public abstract void git_commit(Git git, String msg) throws NoHeadException, NoMessageException, UnmergedPathsException, ConcurrentRefUpdateException, WrongRepositoryStateException, GitAPIException;

}
