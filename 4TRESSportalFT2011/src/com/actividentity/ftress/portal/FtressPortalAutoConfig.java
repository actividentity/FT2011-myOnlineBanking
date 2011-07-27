package com.actividentity.ftress.portal;

import java.rmi.RemoteException;

import com.aspace.ftress.interfaces70.ftress.DTO.ALSI;
import com.aspace.ftress.interfaces70.ftress.DTO.Asset;
import com.aspace.ftress.interfaces70.ftress.DTO.AssetCode;
import com.aspace.ftress.interfaces70.ftress.DTO.AssetGroup;
import com.aspace.ftress.interfaces70.ftress.DTO.AssetGroupCode;
import com.aspace.ftress.interfaces70.ftress.DTO.AssetSet;
import com.aspace.ftress.interfaces70.ftress.DTO.AssetSetCode;
import com.aspace.ftress.interfaces70.ftress.DTO.AuthenticationTypeCode;
import com.aspace.ftress.interfaces70.ftress.DTO.ChannelCode;
import com.aspace.ftress.interfaces70.ftress.DTO.FunctionSet;
import com.aspace.ftress.interfaces70.ftress.DTO.FunctionSetCode;
import com.aspace.ftress.interfaces70.ftress.DTO.FunctionSetPrivilege;
import com.aspace.ftress.interfaces70.ftress.DTO.GroupCode;
import com.aspace.ftress.interfaces70.ftress.DTO.GroupFunctionSetPrivilege;
import com.aspace.ftress.interfaces70.ftress.DTO.SecurityDomain;
import com.aspace.ftress.interfaces70.ftress.DTO.TransactionCode;
import com.aspace.ftress.interfaces70.ftress.DTO.TransactionSet;
import com.aspace.ftress.interfaces70.ftress.DTO.TransactionSetCode;
import com.aspace.ftress.interfaces70.ftress.DTO.TransactionSetItem;
import com.aspace.ftress.interfaces70.ftress.DTO.User;
import com.aspace.ftress.interfaces70.ftress.DTO.UserCode;
import com.aspace.ftress.interfaces70.ftress.DTO.UserGroupAssetSetTransactionSetPrivilege;
import com.aspace.ftress.interfaces70.ftress.DTO.exception.ALSIInvalidException;
import com.aspace.ftress.interfaces70.ftress.DTO.exception.AuthenticationTierException;
import com.aspace.ftress.interfaces70.ftress.DTO.exception.AuthenticatorException;
import com.aspace.ftress.interfaces70.ftress.DTO.exception.ConstraintFailedException;
import com.aspace.ftress.interfaces70.ftress.DTO.exception.CreateDuplicateException;
import com.aspace.ftress.interfaces70.ftress.DTO.exception.IncompatibleAssetGroupException;
import com.aspace.ftress.interfaces70.ftress.DTO.exception.InternalException;
import com.aspace.ftress.interfaces70.ftress.DTO.exception.InvalidChannelException;
import com.aspace.ftress.interfaces70.ftress.DTO.exception.InvalidParameterException;
import com.aspace.ftress.interfaces70.ftress.DTO.exception.NoFunctionPrivilegeException;
import com.aspace.ftress.interfaces70.ftress.DTO.exception.ObjectNotFoundException;
import com.aspace.ftress.interfaces70.ftress.DTO.exception.PasswordExpiredException;
import com.aspace.ftress.interfaces70.ftress.DTO.exception.SeedingException;

public class FtressPortalAutoConfig extends FtressUtils {
	public static final String systemUsername = "system01";
	public static final String systemPassword = "password01";
	
	public static final ChannelCode managementConsoleChannel = new ChannelCode("OPERATOR");
	public static final ChannelCode directChannel = new ChannelCode("CH_DIRECT");
	public static final ChannelCode webBankingChannel = new ChannelCode("CH_BKWEB");
	public static final ChannelCode phoneBankingChannel = new ChannelCode("CH_BKCALL");
	
	public static final AuthenticationTypeCode systemAuthenticationTypeCode = new AuthenticationTypeCode("AT_SYSLOG",false);
	public static final AuthenticationTypeCode managementConsoleStaticLoginAuthenticationTypeCode = new AuthenticationTypeCode("OP_ATCODE",false);
	
	public static final AuthenticationTypeCode customerPKIAuthenticationTypeCode = new AuthenticationTypeCode("AT_CUSTPKI",false);
	public static final AuthenticationTypeCode customerStaticPasswordAuthenticationTypeCode = new AuthenticationTypeCode("AT_CUSTPW",false);
	public static final AuthenticationTypeCode customerOTPAuthenticationTypeCode = new AuthenticationTypeCode("AT_CUSTOTP",false);
	public static final AuthenticationTypeCode customerOOBAuthenticationTypeCode = new AuthenticationTypeCode("AT_CUSTOOB",false);
	public static final AuthenticationTypeCode customerMDAuthenticationTypeCode = new AuthenticationTypeCode("AT_CUSTMD",false);
	
	public static final String systemUserGroupCode = "USG_SYS";
	public static final String customer01UserGroupCode = "USG_CUST1";
	public static final String systemUserType= "UG_SYSTEMS";
	public static final String assetTypeCode="AG_BKACCNT";
	public static final String permissionSetAssetSetResourceType="AssetSet";
	
	public static final SecurityDomain domain1 = new SecurityDomain("FTRESS");
	
	public FtressPortalAutoConfig() throws InternalException, RemoteException {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws InvalidParameterException 
	 * @throws RemoteException 
	 * @throws InternalException 
	 * @throws SeedingException 
	 * @throws InvalidChannelException 
	 * @throws ObjectNotFoundException 
	 * @throws AuthenticationTierException 
	 * @throws PasswordExpiredException 
	 */
	public static void main(String[] args) throws PasswordExpiredException, AuthenticationTierException, ObjectNotFoundException, InvalidChannelException, SeedingException, InternalException, RemoteException, InvalidParameterException {
		FtressPortalAutoConfig ftressPortalAutoConfig = new FtressPortalAutoConfig();
		System.out.println("Starts");
		//ftressPortalAutoConfig.createAllConfigurationForDemo(domain1,"ftadmin","password01");
		ALSI alsi = ftressPortalAutoConfig.primaryAuthentication(ftressPortalAutoConfig.domain1,managementConsoleChannel,managementConsoleStaticLoginAuthenticationTypeCode, "ftadmin", "password01");
		GroupFunctionSetPrivilege[] groupFunctionSetPrivilege = null;
		try {
			groupFunctionSetPrivilege =  ftressPortalAutoConfig.userGroupFunctionPrivilegeManager.getUserGroupFunctionSetPrivileges(alsi, managementConsoleChannel,new GroupCode(ftressPortalAutoConfig.systemUserGroupCode,false), ftressPortalAutoConfig.domain1);
			ftressPortalAutoConfig.updateSystemGroupPrivilegesForDemo(ftressPortalAutoConfig.domain1, alsi);
			System.out.println("Done...");
		} catch (ALSIInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoFunctionPrivilegeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ends");
		
	}
	
	public boolean createAllConfigurationForDemo(SecurityDomain domain,String username,String password){
		ALSI alsi=null;
		try {
			alsi = primaryAuthentication(domain,managementConsoleChannel,managementConsoleStaticLoginAuthenticationTypeCode, username, password);
			System.out.println("createAllConfigurationForDemo     primary authentication success");
		} catch (PasswordExpiredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthenticationTierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidChannelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SeedingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(createSystemUser(domain, alsi, systemUsername)){
			System.out.println("createAllConfigurationForDemo     system user created");
			if(createSystemPassword(domain, alsi, systemUsername, systemPassword)){
				System.out.println("createAllConfigurationForDemo     system user password created");
				try {
					if(!isSessionValid(domain,verifyUserPassword(domain, alsi, directChannel, systemAuthenticationTypeCode, systemUsername, systemPassword,null))){
						System.out.println("createAllConfigurationForDemo     Warning password was not enforced. You will need to update the " + systemUsername + " " + systemAuthenticationTypeCode + " password in the functions_common.jsp");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("createAllConfigurationForDemo     Warning password was not enforced. You will need to update the " + systemUsername + " " + systemAuthenticationTypeCode + " password in the functions_common.jsp");
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				if(updateSystemGroupPrivilegesForDemo(domain, alsi)){
					System.out.println("createAllConfigurationForDemo     system privileges updated");
					if(createAssetTypeForDemo(domain, alsi)){
						System.out.println("createAllConfigurationForDemo     AssetType created");
						if(createAssetSetForDemo(domain, alsi)){
							System.out.println("createAllConfigurationForDemo     AssetSets created");
							if(createAssetsForDemo(domain, alsi)){
								System.out.println("createAllConfigurationForDemo     Assets created");
								if(createPermissionSetForDemo(domain, alsi)){
									System.out.println("createAllConfigurationForDemo     PermissionSets created");
									if(grantPermissionSetForDemo(domain, alsi)){
										System.out.println("createAllConfigurationForDemo     PermissionSets granted");
										System.out.println("createAllConfigurationForDemo     Demo application configured !!");
										return true;
									}else{
										System.out.println("createAllConfigurationForDemo     PermissionSets grant failed");
									}
								}else{
									System.out.println("createAllConfigurationForDemo     PermissionSets creation failed");
								}
							}else{
								System.out.println("createAllConfigurationForDemo     Assets creation failed");
							}
						}else{
							System.out.println("createAllConfigurationForDemo     AssetSets creation failed");
						}
					}else{
						System.out.println("createAllConfigurationForDemo     AssetType creation failed");
					}
				}else{
					System.out.println("createAllConfigurationForDemo     system privileges update failed");
				}
			}else{
				System.out.println("createAllConfigurationForDemo     system user password creation failed");
			}
		}else{
			System.out.println("createAllConfigurationForDemo     system user creation failed");
		}
		return false;
	}
	@SuppressWarnings("finally")
	public boolean createSystemUser(SecurityDomain domain,ALSI alsi,String username) {
		try {
			//System.out.println("about to create system user.");
			User user = new User();
			user.setCode(new UserCode(username));
			user.setUserType(systemUserType);
			user.setGroupCode(new GroupCode(systemUserGroupCode, false));
			//Creates user
			user = userManagerInterface.createUser(alsi, managementConsoleChannel, user, domain);
			System.out.println("createSystemUser    system user created.");
			return true;
		} catch (ALSIInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NoFunctionPrivilegeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (CreateDuplicateException e) {
			// TODO Auto-generated catch block
			return true;
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean createSystemPassword(SecurityDomain domain,ALSI alsi,String username,String password){
		try {
			createUPauthenticator(domain, alsi, managementConsoleChannel, systemAuthenticationTypeCode, username, password);
			return true;
		} catch (AuthenticatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (CreateDuplicateException e) {
			// TODO Auto-generated catch block
			return true;
		} catch (ConstraintFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InvalidChannelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NoFunctionPrivilegeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ALSIInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("finally")
	public boolean updateSystemGroupPrivilegesForDemo(SecurityDomain domain,ALSI alsi){
		ChannelCode allChannels = new ChannelCode();
		allChannels.setAllChannels(true);
		
		FunctionSetPrivilege[] functionSetPrivileges = new FunctionSetPrivilege[6];
		
		//System Privilege (Full) for any any channel any authentication policy and Customer group 1
		FunctionSetPrivilege functionSetPrivilege1 = new FunctionSetPrivilege();
		functionSetPrivilege1.setFunctionSetCode(new FunctionSetCode("FS_SYSFULL"));
		functionSetPrivilege1.setChannelCode(allChannels);
		//functionSetPrivilege1.setAuthenticationTypeCode(systemAuthenticationTypeCode);
		functionSetPrivilege1.setAuthenticationTypeCode(new AuthenticationTypeCode(null,true));
		functionSetPrivilege1.setGroupCondition(new GroupCode("USG_CUST1",false));
		
		//User Sub-Group Administration functions functions for any any channel any authentication policy and any resource
		FunctionSetPrivilege functionSetPrivilege2 = new FunctionSetPrivilege();
		functionSetPrivilege2.setFunctionSetCode(new FunctionSetCode("FS_GRPADIM"));
		functionSetPrivilege2.setChannelCode(allChannels);
		//functionSetPrivilege2.setAuthenticationTypeCode(systemAuthenticationTypeCode);
		functionSetPrivilege2.setAuthenticationTypeCode(new AuthenticationTypeCode(null,true));
		functionSetPrivilege2.setGroupCondition(new GroupCode(null,true));

		//Device Administration functions for any any channel any authentication policy and any resource
		FunctionSetPrivilege functionSetPrivilege3 = new FunctionSetPrivilege();
		functionSetPrivilege3.setFunctionSetCode(new FunctionSetCode("FS_DEVADMN"));
		functionSetPrivilege3.setChannelCode(allChannels);
		//functionSetPrivilege3.setAuthenticationTypeCode(systemAuthenticationTypeCode);
		functionSetPrivilege3.setAuthenticationTypeCode(new AuthenticationTypeCode(null,true));
		functionSetPrivilege3.setGroupCondition(new GroupCode(null,true));
		
		//Asset Type Administration functions functions for any any channel any authentication policy and any resource
		FunctionSetPrivilege functionSetPrivilege4 = new FunctionSetPrivilege();
		functionSetPrivilege4.setFunctionSetCode(new FunctionSetCode("FS_ASMNGT"));
		functionSetPrivilege4.setChannelCode(allChannels);
		//functionSetPrivilege4.setAuthenticationTypeCode(systemAuthenticationTypeCode);
		functionSetPrivilege4.setAuthenticationTypeCode(new AuthenticationTypeCode(null,true));
		functionSetPrivilege4.setGroupCondition(new GroupCode(null,true));
		
		//Soft Token Portal Functions functions for any any channel any authentication policy and any resource
		FunctionSetPrivilege functionSetPrivilege5 = new FunctionSetPrivilege();
		functionSetPrivilege5.setFunctionSetCode(new FunctionSetCode("FS_STPADM"));
		functionSetPrivilege5.setChannelCode(allChannels);
		//functionSetPrivilege5.setAuthenticationTypeCode(systemAuthenticationTypeCode);
		functionSetPrivilege5.setAuthenticationTypeCode(new AuthenticationTypeCode(null,true));
		functionSetPrivilege5.setGroupCondition(new GroupCode(null,true));
		
		//System Privilege (Full) for any any channel any authentication policy and Customer group 2
		FunctionSetPrivilege functionSetPrivilege6 = new FunctionSetPrivilege();
		functionSetPrivilege6.setFunctionSetCode(new FunctionSetCode("FS_SYSFULL"));
		functionSetPrivilege6.setChannelCode(allChannels);
		//functionSetPrivilege6.setAuthenticationTypeCode(systemAuthenticationTypeCode);
		functionSetPrivilege6.setAuthenticationTypeCode(new AuthenticationTypeCode(null,true));
		functionSetPrivilege6.setGroupCondition(new GroupCode("USG_CUST2",false));
		
		functionSetPrivileges[0] = functionSetPrivilege1;
		functionSetPrivileges[1] = functionSetPrivilege2;
		functionSetPrivileges[2] = functionSetPrivilege3;
		functionSetPrivileges[3] = functionSetPrivilege4;
		functionSetPrivileges[4] = functionSetPrivilege5;
		functionSetPrivileges[5] = functionSetPrivilege6;
		
		GroupFunctionSetPrivilege[] groupFunctionSetPrivileges;
		try {
			groupFunctionSetPrivileges = userGroupFunctionPrivilegeManager.addUserSubGroupFunctionSetPrivileges(alsi, managementConsoleChannel, new GroupCode(systemUserGroupCode,false), functionSetPrivileges, domain);
			if(groupFunctionSetPrivileges!=null){
				return true;
			}else{
				return false;
			}
		} catch (ALSIInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NoFunctionPrivilegeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (CreateDuplicateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	@SuppressWarnings("finally")
	public boolean createAssetType(SecurityDomain domain,ALSI alsi,String assetTypeName) {
		AssetGroup assetGroup = new AssetGroup();
		assetGroup.setCode(new AssetGroupCode(assetTypeCode));
		assetGroup.setName(assetTypeName);
		try {
			assetGroup= assetManager.createAssetGroup(alsi, managementConsoleChannel,assetGroup , domain);
			if(assetGroup!=null){
				return true;
			}else{
				return false;
			}
		} catch (ALSIInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NoFunctionPrivilegeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (CreateDuplicateException e) {
			// TODO Auto-generated catch block
			return true;
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	public boolean createAssetTypeForDemo(SecurityDomain domain,ALSI alsi) {
		return createAssetType(domain, alsi, assetTypeCode);
	}

	private boolean createAssetSet(SecurityDomain domain,ALSI alsi,String assetSetName,String assetSetCode){
		AssetSet assetSet = new AssetSet();
		assetSet.setAssetGroupCode(new AssetGroupCode(assetTypeCode));
		assetSet.setCode(new AssetSetCode(assetSetCode));
		assetSet.setName(assetSetName);
		assetSet.setNotes(assetSetName + " AssetSet");
		
		try {
			assetSet = assetManager.createAssetSet(alsi, managementConsoleChannel, assetSet, domain);
			if(assetSet!=null){
				return true;
			}else{
				return false;
			}
		} catch (ALSIInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NoFunctionPrivilegeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (CreateDuplicateException e) {
			// TODO Auto-generated catch block
			return true;
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean createAssetSetForDemo(SecurityDomain domain,ALSI alsi){
		if(!createAssetSet(domain, alsi, "Loan Accounts", "AS_BKLO")){
			return false;
		}else if(!createAssetSet(domain, alsi, "Saving Accounts", "AS_BKSA")){
			return false;
		}else if(!createAssetSet(domain, alsi, "All Accounts", "AS_BKALL")){
			return false;
		}else if(!createAssetSet(domain, alsi, "Credit Accounts", "AS_BKCR")){
			return false;
		}else{
			return true;
		}
	}
	private boolean createAssets(SecurityDomain domain,ALSI alsi,String assetName,String assetCode){
		Asset asset = new Asset();
		asset.setAssetGroupCode(new AssetGroupCode(assetTypeCode));
		asset.setCode(new AssetCode(assetCode));
		asset.setName(assetName);
		asset.setNotes(assetName + " Asset");
		
		try {
			asset = assetManager.createAsset(alsi, managementConsoleChannel, asset, domain);
			if(asset!=null){
				return true;
			}else{
				return false;
			}
		} catch (ALSIInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NoFunctionPrivilegeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (CreateDuplicateException e) {
			// TODO Auto-generated catch block
			return true;
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean createAssetsForDemo(SecurityDomain domain,ALSI alsi){
		
		try {
			//Mortgage Account
			createAssets(domain, alsi, "Mortgage Account", "A_BKMORT");
			assetManager.addAssetToAssetSet(alsi, managementConsoleChannel, new AssetCode("A_BKMORT"), new AssetSetCode("AS_BKLO"), domain);
			assetManager.addAssetToAssetSet(alsi, managementConsoleChannel, new AssetCode("A_BKMORT"), new AssetSetCode("AS_BKALL"), domain);
			
			//Visa Credit Card
			createAssets(domain, alsi, "Visa Credit Card", "A_BKVISA");
			assetManager.addAssetToAssetSet(alsi, managementConsoleChannel, new AssetCode("A_BKVISA"), new AssetSetCode("AS_BKCR"), domain);
			assetManager.addAssetToAssetSet(alsi, managementConsoleChannel, new AssetCode("A_BKVISA"), new AssetSetCode("AS_BKALL"), domain);
			
			//Stocks Account
			createAssets(domain, alsi, "Stocks Account", "A_BKSTOCKS");
			assetManager.addAssetToAssetSet(alsi, managementConsoleChannel, new AssetCode("A_BKSTOCKS"), new AssetSetCode("AS_BKSA"), domain);
			assetManager.addAssetToAssetSet(alsi, managementConsoleChannel, new AssetCode("A_BKSTOCKS"), new AssetSetCode("AS_BKALL"), domain);
			
			//Current Account
			createAssets(domain, alsi, "Current Account", "A_BKMAIN");
			assetManager.addAssetToAssetSet(alsi, managementConsoleChannel, new AssetCode("A_BKMAIN"), new AssetSetCode("AS_BKALL"), domain);
			
			return true;
		} catch (ALSIInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IncompatibleAssetGroupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NoFunctionPrivilegeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean createPermissionSet(SecurityDomain domain,ALSI alsi,TransactionSet transactionSet){
		try {
			authorisationTransactionManager.createTransactionSet(alsi, managementConsoleChannel, transactionSet, domain);
			return true;
		} catch (ALSIInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NoFunctionPrivilegeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (CreateDuplicateException e) {
			// TODO Auto-generated catch block
			return true;
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean createPermissionSetForDemo(SecurityDomain domain,ALSI alsi){
		
		
			//Banking Credit no limit
			TransactionSet transactionSet = new TransactionSet();
			transactionSet.setName("Banking Credit no limit");
			transactionSet.setCode(new TransactionSetCode("TS_BKCR"));
			transactionSet.setResourceType(permissionSetAssetSetResourceType);
			TransactionSetItem[] transactionSetItems = new TransactionSetItem[1];
			transactionSetItems[0] = new TransactionSetItem();
			transactionSetItems[0].setTransactionsCode(new TransactionCode("TR_CREDIT"));
			//transactionSetItems[0].setThreshold(threshold);
			transactionSet.setTransactionSetItems(transactionSetItems);
			if(!createPermissionSet(domain,alsi,transactionSet)){
				return false;
			}
			
			//Banking Withdrawal no limit
			transactionSet = new TransactionSet();
			transactionSet.setName("Banking Withdrawal no limit");
			transactionSet.setCode(new TransactionSetCode("TS_BKDB"));
			transactionSet.setResourceType(permissionSetAssetSetResourceType);
			transactionSetItems = new TransactionSetItem[1];
			transactionSetItems[0] = new TransactionSetItem();
			transactionSetItems[0].setTransactionsCode(new TransactionCode("TR_WITHDRA"));
			//transactionSetItems[0].setThreshold(threshold);
			transactionSet.setTransactionSetItems(transactionSetItems);
			if(!createPermissionSet(domain,alsi,transactionSet)){
				return false;
			}
			
			//Banking Withdrawal 100
			transactionSet = new TransactionSet();
			transactionSet.setName("Banking Withdrawal 100");
			transactionSet.setCode(new TransactionSetCode("TS_BKDB01"));
			transactionSet.setResourceType(permissionSetAssetSetResourceType);
			transactionSetItems = new TransactionSetItem[1];
			transactionSetItems[0] = new TransactionSetItem();
			transactionSetItems[0].setTransactionsCode(new TransactionCode("TR_WITHDRA"));
			transactionSetItems[0].setThreshold("100");
			transactionSet.setTransactionSetItems(transactionSetItems);
			if(!createPermissionSet(domain,alsi,transactionSet)){
				return false;
			}
			
			//Banking Withdrawal 1.000
			transactionSet = new TransactionSet();
			transactionSet.setName("Banking Withdrawal 1.000");
			transactionSet.setCode(new TransactionSetCode("TS_BKDB02"));
			transactionSet.setResourceType(permissionSetAssetSetResourceType);
			transactionSetItems = new TransactionSetItem[1];
			transactionSetItems[0] = new TransactionSetItem();
			transactionSetItems[0].setTransactionsCode(new TransactionCode("TR_WITHDRA"));
			transactionSetItems[0].setThreshold("1000");
			transactionSet.setTransactionSetItems(transactionSetItems);
			if(!createPermissionSet(domain,alsi,transactionSet)){
				return false;
			}
			
			//Banking Withdrawal 10.000
			transactionSet = new TransactionSet();
			transactionSet.setName("Banking Withdrawal 10.000");
			transactionSet.setCode(new TransactionSetCode("TS_BKDB03"));
			transactionSet.setResourceType(permissionSetAssetSetResourceType);
			transactionSetItems = new TransactionSetItem[1];
			transactionSetItems[0] = new TransactionSetItem();
			transactionSetItems[0].setTransactionsCode(new TransactionCode("TR_WITHDRA"));
			transactionSetItems[0].setThreshold("10000");
			transactionSet.setTransactionSetItems(transactionSetItems);
			if(!createPermissionSet(domain,alsi,transactionSet)){
				return false;
			}
			
			return true;
		
	}
	public boolean grantPermissionSet(SecurityDomain domain,ALSI alsi,UserGroupAssetSetTransactionSetPrivilege userGroupAssetSetTransactionSetPrivilege){
		try {
			userGroupAssetPrivilegeManager.addUserSubGroupAssetSetTransactionSetPrivilege(alsi,managementConsoleChannel,new GroupCode(customer01UserGroupCode,false),userGroupAssetSetTransactionSetPrivilege,domain);
			return true;
		} catch (ALSIInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NoFunctionPrivilegeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (CreateDuplicateException e) {
			// TODO Auto-generated catch block
			return true;
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean grantPermissionSetForDemo(SecurityDomain domain,ALSI alsi){
			return grantPermissionSetForDemo(domain, alsi,webBankingChannel,phoneBankingChannel);
	}
	public boolean grantPermissionSetForDemo(SecurityDomain domain,ALSI alsi,ChannelCode customerPortalChannel,ChannelCode callCenterChannel){
		ChannelCode allChannels = new ChannelCode("");
		allChannels.setAllChannels(true);
		
		UserGroupAssetSetTransactionSetPrivilege userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerPKIAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerMDAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerMDAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(callCenterChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerOOBAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerOOBAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(callCenterChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerOTPAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerOTPAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(callCenterChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerPKIAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerPKIAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(callCenterChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerStaticPasswordAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerStaticPasswordAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(callCenterChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerOTP);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB03"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}

		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerMDAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB01"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerMDAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(callCenterChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB01"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerOOBAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB01"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerOOBAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(callCenterChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB01"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerOTPAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB01"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerOTPAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(callCenterChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB01"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerPKIAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB01"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerPKIAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(callCenterChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB01"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerStaticPasswordAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB01"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKALL"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerStaticPasswordAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(callCenterChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB01"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerOTP);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB02"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerOTP);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(callCenterChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB02"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerOOBAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB02"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerOOBAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(callCenterChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB02"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerPKIAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(customerPortalChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB02"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		userGroupAssetSetTransactionSetPrivilege = new UserGroupAssetSetTransactionSetPrivilege();
		userGroupAssetSetTransactionSetPrivilege.setAssetSetCode(new AssetSetCode("AS_BKCR"));
		userGroupAssetSetTransactionSetPrivilege.setAuthenticationTypeCode(customerPKIAuthenticationTypeCode);
		userGroupAssetSetTransactionSetPrivilege.setChannelCode(callCenterChannel);
		userGroupAssetSetTransactionSetPrivilege.setTransactionSetCode(new TransactionSetCode("TS_BKDB02"));
		userGroupAssetSetTransactionSetPrivilege.setGroupCode(new GroupCode(customer01UserGroupCode,false));
		if(!grantPermissionSet(domain,alsi,userGroupAssetSetTransactionSetPrivilege)){
			return false;
		}
		return true;
	}
}
