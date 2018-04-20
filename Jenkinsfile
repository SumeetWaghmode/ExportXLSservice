#!/usr/bin/env groovy


/**==============================================================================================
                Copyright (c) Unpublished - All Rights Reserved
=================================================================================================
File description:

    Filename	: Jenkinsfile
	Description : This is a groovy script used for building MAVEN project.

    USAGE : This script has to be placed directly under your Source Control Tool (GIT) and Jenkins
	must be configured with a Multibranch Pipeline project to connect to the GIT Repo.
	
	There are certain parameters added to the script and has some default values. DO NOT change 
	these default values directly in the script unless the locations are changed in the server.
	If someone wants to change the values, then they can change it when running it in Jenkins as a 
	parameter for the job and not in the file itself.

===================================================================================================
File History
Name					Date		Description of change
===================================================================================================

Sumeet Waghmode		19-April-2018		Initial Creation
===================================================================================================*/

properties([parameters([                  
						string(defaultValue: 'E:/SUMEET/Projects/AutoBuild-DeployProjects/Intranet/Project', description: 'TC_ROOT Location in windows. Forward slash(/) must be used as file seperator', name: 'Workspace'),											
						string(defaultValue: '/media/SVN/branches/R1.4.2/Customization', description: 'Customization folder Location. Forward slash(/) must be used as file seperator', name: 'WORKSPACE_LOC'),						
						string(defaultValue: '/media/SVN/branches/R1.4.2/Binaries', description: 'This is path , where script will create datestamp folder and will put binaries inside this folder. Forward slash(/) must be used as file seperator', name: 'Binary_Location'),				
						])])

  stage 'Build branch'
  println "Current branch ${env.BRANCH_NAME}"
  @Library('Jenkins_Sample_SharedLib')_
  
   parallel(
	"Linux" : 
	{

	},
	"Windows" : 
	{
			node
			{
				//AntHome = tool 'AntHome'
				println "${params.Workspace}"
				ws("${params.Workspace}")
				{	
					stage("Win Checkout")
					{	
						//def dir =${params.Workspace}
						echo 'Checkout....'
						checkoutRepos()
						
					}
					
					stage("Build Maven")
					{	
						try
						{
							echo 'Building maven project'	
							//def projectDir=${params.Workspace}+'/'+"ExportXlsService"
							buildMaven("E:/SUMEET/Projects/AutoBuild-DeployProjects/Intranet/Project/ExportXlsService");
						}
						catch(e)
						{
								 currentBuild.result = 'FAILURE'								
						}
						
					}
	
					
					
				}	
			}
		
	},
)