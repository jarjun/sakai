/**
 * $URL:  $
 * $Id$
 *
 * Copyright (c) 2006-2009 The Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://www.opensource.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sakaiproject.dash.jobs;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
//TODO: Find all statsUpdateManager and replace with this dashboard job

public class DashRepeatEventJob extends DashQuartzJob {
	private Log	logger = LogFactory.getLog(DashRepeatEventJob.class);
	
	//Matches the bean id
	final static String beanId = "dashRepeatEventJob";
	 
	//Matches the jobName
	final static String jobName = "Dashboard Repeat Event Job";
	 
	public void init() {
		super.init();
	    logger.info(this + " init()");
	}

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
	    String quartzServer = sakaiProxy.getConfigParam("dashboard_quartzServer", null);
	    String serverName = sakaiProxy.getServerId();
    	if (quartzServer != null && serverName != null && quartzServer.equals(serverName))
    	{	
    		logger.info(this + " execute: " + getConfigMessage());
            
    		try {
				dashboardCommonLogic.updateRepeatingEvents();
			} catch (Exception e) {
				logger.warn("Error executing dashboard quartz job for dashboard repeating events " , e);
			}
    	}
    }

}


