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

properties([parameters([string(defaultValue: '/media', description: 'Windows Build Location. Forward slash(/) must be used as file seperator', name: 'WinBuildLoc'), 
                        string(defaultValue: '/hd2a/home/tcc43c01/iec_auto_build_do_not_delete', description: 'Linux Build Location. Forward slash(/) must be used as file seperator', name: 'LnxBuildLOC'),
						string(defaultValue: 'C:/Program Files (x86)/Microsoft Visual Studio 11.0/VC', description: 'Visual Studio Installation Location. Forward slash(/) must be used as file seperator', name: 'MSDEV_HOME_Win'),
						string(defaultValue: 'N:/ugs/tc11/11', description: 'TC_ROOT Location in windows. Forward slash(/) must be used as file seperator', name: 'Workspace'),
						string(defaultValue: 'N:/ugs/tc11/tcdata', description: 'TC_DATA Location in windows. Forward slash(/) must be used as file seperator', name: 'TC_DATA_Win'),
						string(defaultValue: '/home/tci01c01/appl/tc11_2', description: 'TC_ROOT Location in linux. Forward slash(/) must be used as file seperator', name: 'TC_ROOT_LNX'),
						string(defaultValue: '/home/tci01c01/data/tc11_2', description: 'TC_DATA Location in linux. Forward slash(/) must be used as file seperator', name: 'TC_DATA_LNX'),						
						string(defaultValue: '/media/SVN/branches/R1.4.2/Customization', description: 'Customization folder Location. Forward slash(/) must be used as file seperator', name: 'WORKSPACE_LOC'),						
						string(defaultValue: '/media/SVN/branches/R1.4.2/Binaries', description: 'This is path , where script will create datestamp folder and will put binaries inside this folder. Forward slash(/) must be used as file seperator', name: 'Binary_Location'),						
						choice(choices: "All\nWinLibs\nWinUtils\nWinJars\nWinDISP\nWinBMIDE\nLNXLibs\nLNXJARs", description: 'Select what component you want to build. Defaults to All', name: 'component_to_build'),
						choice(choices: "Build\nRebuild\nClean", description: 'Select Target want to build. Defaults to Build', name: 'Target'),						
						string(defaultValue: 'N:/ugs/tc11/11_disp', description: 'Dispatcher client root , used in building Dispatcher project.', name: 'DISP_CLIENT_ROOT'),
						string(defaultValue: 'R1.4.2', description: 'Release number.', name: 'Build_Note'),						
						])])

  stage 'Build branch'
  println "Current branch ${env.BRANCH_NAME}"
  
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
							checkoutRepos '${params.Workspace}'
					}
					
					stage("Build Maven")
					{	
						echo 'Building maven project'	
					}
	
					
					
				}	
			}
		
	},
)