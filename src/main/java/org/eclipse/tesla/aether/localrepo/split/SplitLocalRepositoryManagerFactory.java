package org.eclipse.tesla.aether.localrepo.split;

/*******************************************************************************
 * Copyright (c) 2011 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.LocalRepositoryManager;
import org.eclipse.aether.repository.NoLocalRepositoryManagerException;
import org.eclipse.aether.spi.localrepo.LocalRepositoryManagerFactory;
import org.eclipse.aether.spi.locator.Service;
import org.eclipse.aether.spi.locator.ServiceLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates local repository managers for the local repository types {@code "split"} and {@code ""} (automatic).
 */
@Singleton
@Named("split")
public class SplitLocalRepositoryManagerFactory
    implements LocalRepositoryManagerFactory, Service
{

  @Inject
  private Logger logger = LoggerFactory.getLogger(SplitLocalRepositoryManagerFactory.class);

  public LocalRepositoryManager newInstance(final RepositorySystemSession session, final LocalRepository repository)
      throws NoLocalRepositoryManagerException
  {
    if ("".equals(repository.getContentType()) || "split".equals(repository.getContentType())) {
      return new SplitLocalRepositoryManager(repository.getBasedir());
    }
    else {
      throw new NoLocalRepositoryManagerException(repository);
    }
  }

  public void initService(ServiceLocator locator) {
  }

  public float getPriority() {
    return 50;
  }

}
