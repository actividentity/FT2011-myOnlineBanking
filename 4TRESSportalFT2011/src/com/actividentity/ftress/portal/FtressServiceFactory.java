package com.actividentity.ftress.portal;
 

import java.rmi.RemoteException;

import javax.ejb.CreateException;

import com.aspace.ftress.interfaces70.ejb.AssetAuthorisor;
import com.aspace.ftress.interfaces70.ejb.AssetAuthorisorHome;
import com.aspace.ftress.interfaces70.ejb.AssetManager;
import com.aspace.ftress.interfaces70.ejb.AssetManagerHome;
import com.aspace.ftress.interfaces70.ejb.Auditor;
import com.aspace.ftress.interfaces70.ejb.AuditorHome;
import com.aspace.ftress.interfaces70.ejb.Authenticator;
import com.aspace.ftress.interfaces70.ejb.AuthenticatorConfiguration;
import com.aspace.ftress.interfaces70.ejb.AuthenticatorConfigurationHome;
import com.aspace.ftress.interfaces70.ejb.AuthenticatorHome;
import com.aspace.ftress.interfaces70.ejb.AuthenticatorManager;
import com.aspace.ftress.interfaces70.ejb.AuthenticatorManagerHome;
import com.aspace.ftress.interfaces70.ejb.AuthorisationTransactionConfiguration;
import com.aspace.ftress.interfaces70.ejb.AuthorisationTransactionConfigurationHome;
import com.aspace.ftress.interfaces70.ejb.AuthorisationTransactionManager;
import com.aspace.ftress.interfaces70.ejb.AuthorisationTransactionManagerHome;
import com.aspace.ftress.interfaces70.ejb.Authorisor;
import com.aspace.ftress.interfaces70.ejb.AuthorisorHome;
import com.aspace.ftress.interfaces70.ejb.BatchProcessor;
import com.aspace.ftress.interfaces70.ejb.BatchProcessorHome;
import com.aspace.ftress.interfaces70.ejb.CredentialManager;
import com.aspace.ftress.interfaces70.ejb.CredentialManagerHome;
import com.aspace.ftress.interfaces70.ejb.DeviceConfiguration;
import com.aspace.ftress.interfaces70.ejb.DeviceConfigurationHome;
import com.aspace.ftress.interfaces70.ejb.DeviceManager;
import com.aspace.ftress.interfaces70.ejb.DeviceManagerHome;
//import com.aspace.ftress.interfaces70.ejb.FormManager;
//import com.aspace.ftress.interfaces70.ejb.FormManagerHome;
import com.aspace.ftress.interfaces70.ejb.FunctionManager;
import com.aspace.ftress.interfaces70.ejb.FunctionManagerHome;
import com.aspace.ftress.interfaces70.ejb.PrincipalConfiguration;
import com.aspace.ftress.interfaces70.ejb.PrincipalConfigurationHome;
import com.aspace.ftress.interfaces70.ejb.RoleAssetPrivilegeManager;
import com.aspace.ftress.interfaces70.ejb.RoleAssetPrivilegeManagerHome;
import com.aspace.ftress.interfaces70.ejb.RoleAuthorisationPrivilegeManager;
import com.aspace.ftress.interfaces70.ejb.RoleAuthorisationPrivilegeManagerHome;
import com.aspace.ftress.interfaces70.ejb.RoleFunctionPrivilegeManager;
import com.aspace.ftress.interfaces70.ejb.RoleFunctionPrivilegeManagerHome;
import com.aspace.ftress.interfaces70.ejb.RoleManager;
import com.aspace.ftress.interfaces70.ejb.RoleManagerHome;
import com.aspace.ftress.interfaces70.ejb.SessionTransfer;
import com.aspace.ftress.interfaces70.ejb.SessionTransferHome;
import com.aspace.ftress.interfaces70.ejb.SystemConfiguration;
import com.aspace.ftress.interfaces70.ejb.SystemConfigurationHome;
import com.aspace.ftress.interfaces70.ejb.UserAssetPrivilegeManager;
import com.aspace.ftress.interfaces70.ejb.UserAssetPrivilegeManagerHome;
import com.aspace.ftress.interfaces70.ejb.UserAuthorisationPrivilegeManager;
import com.aspace.ftress.interfaces70.ejb.UserAuthorisationPrivilegeManagerHome;
import com.aspace.ftress.interfaces70.ejb.UserFunctionPrivilegeManager;
import com.aspace.ftress.interfaces70.ejb.UserFunctionPrivilegeManagerHome;
import com.aspace.ftress.interfaces70.ejb.UserGroupAssetPrivilegeManager;
import com.aspace.ftress.interfaces70.ejb.UserGroupAssetPrivilegeManagerHome;
import com.aspace.ftress.interfaces70.ejb.UserGroupAuthorisationPrivilegeManager;
import com.aspace.ftress.interfaces70.ejb.UserGroupAuthorisationPrivilegeManagerHome;
import com.aspace.ftress.interfaces70.ejb.UserGroupFunctionPrivilegeManager;
import com.aspace.ftress.interfaces70.ejb.UserGroupFunctionPrivilegeManagerHome;
import com.aspace.ftress.interfaces70.ejb.UserGroupManager;
import com.aspace.ftress.interfaces70.ejb.UserGroupManagerHome;
import com.aspace.ftress.interfaces70.ejb.UserManager;
import com.aspace.ftress.interfaces70.ejb.UserManagerHome;
import com.aspace.ftress.interfaces70.ftress.DTO.exception.InternalException;


/**
 * This helper class is responsible for resolving EJB home factories into remote object references.
 * 
 * @see com.aspace.ftress.business.common.ServiceLocator
 * @since 3.2
 * @author spark
 */
public final class FtressServiceFactory {

    public static final String FTRESS_EJB_ASSET_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_ASSET_MANAGER", "ejb/4TRESSAssetManager70");

    public static final String FTRESS_EJB_ASSET_TRANSACTION_AUTHORISOR = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_ASSET_TRANSACTION_AUTHORISOR", "ejb/4TRESSAssetAuthorisor70");

    public static final String FTRESS_EJB_AUDITOR = FtressConfiguration.getProperty("JNDI_FTRESS_EJB_AUDITOR",
            "ejb/4TRESSAuditor70");

    public static final String FTRESS_EJB_AUTHENTICATOR = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_AUTHENTICATOR", "ejb/4TRESSAuthenticator70");

    public static final String FTRESS_EJB_AUTHENTICATOR_CONFIGURATION = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_AUTHENTICATOR_CONFIGURATION", "ejb/4TRESSAuthenticatorConfiguration70");

    public static final String FTRESS_EJB_AUTHENTICATOR_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_AUTHENTICATOR_MANAGER", "ejb/4TRESSAuthenticatorManager70");

    public static final String FTRESS_EJB_BATCH_PROCESSOR = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_BATCH_PROCESSOR", "ejb/4TRESSBatchProcessor70");

    public static final String FTRESS_EJB_CREDENTIAL_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_CREDENTIAL_MANAGER", "ejb/4TRESSCredentialManager70");

    public static final String FTRESS_EJB_DEVICE_CONFIGURATION = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_DEVICE_CONFIGURATION", "ejb/4TRESSDeviceConfiguration70");

    public static final String FTRESS_EJB_DEVICE_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_DEVICE_MANAGER", "ejb/4TRESSDeviceManager70");

    public static final String FTRESS_EJB_FORM_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_FORM_MANAGER", "ejb/4TRESSFormManager70");

    public static final String FTRESS_EJB_FUNCTION_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_FUNCTION_MANAGER", "ejb/4TRESSFunctionManager70");

    public static final String FTRESS_EJB_ROLE_ASSET_PRIVILEGE_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_ROLE_ASSET_PRIVILEGE_MANAGER", "ejb/4TRESSRoleAssetPrivilegeManager70");

    public static final String FTRESS_EJB_PRINCIPAL_CONFIGURATION = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_PRINCIPAL_CONFIGURATION", "ejb/4TRESSPrincipalConfiguration70");

    public static final String FTRESS_EJB_ROLE_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_ROLE_MANAGER", "ejb/4TRESSRoleManager70");

    public static final String FTRESS_EJB_ROLE_FUNCTION_PRIVILEGE_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_ROLE_FUNCTION_PRIVILEGE_MANAGER", "ejb/4TRESSRoleFunctionPrivilegeManager70");

    public static final String FTRESS_EJB_ROLE_TRANSACTION_AUTHORISATION_PRIVILEGE_MANAGER = FtressConfiguration
            .getProperty("JNDI_FTRESS_EJB_ROLE_TRANSACTION_AUTHORISATION_PRIVILEGE_MANAGER",
                    "ejb/4TRESSRoleAuthorisationPrivilegeManager70");

    public static final String FTRESS_EJB_SESSION_TRANSFER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_SESSION_TRANSFER", "ejb/4TRESSSessionTransfer70");

    public static final String FTRESS_EJB_SYSTEM_CONFIGURATION = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_SYSTEM_CONFIGURATION", "ejb/4TRESSSystemConfiguration70");

    public static final String FTRESS_EJB_TRANSACTION_AUTHORISATION_CONFIGURATION = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_TRANSACTION_AUTHORISATION_CONFIGURATION",
            "ejb/4TRESSAuthorisationTransactionConfiguration70");

    public static final String FTRESS_EJB_TRANSACTION_AUTHORISATION_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_TRANSACTION_AUTHORISATION_MANAGER", "ejb/4TRESSAuthorisationTransactionManager70");

    public static final String FTRESS_EJB_TRANSACTION_AUTHORISOR = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_TRANSACTION_AUTHORISOR", "ejb/4TRESSAuthorisor70");

    public static final String FTRESS_EJB_USER_ASSET_PRIVILEGE_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_USER_ASSET_PRIVILEGE_MANAGER", "ejb/4TRESSUserAssetPrivilegeManager70");

    public static final String FTRESS_EJB_USER_FUNCTION_PRIVILEGE_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_USER_FUNCTION_PRIVILEGE_MANAGER", "ejb/4TRESSUserFunctionPrivilegeManager70");

    public static final String FTRESS_EJB_USER_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_USER_MANAGER", "ejb/4TRESSUserManager70");

    public static final String FTRESS_EJB_USER_GROUP_ASSET_PRIVILEGE_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_USER_GROUP_ASSET_PRIVILEGE_MANAGER", "ejb/4TRESSUserGroupAssetPrivilegeManager70");

    public static final String FTRESS_EJB_USER_GROUP_FUNCTION_PRIVILEGE_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_USER_GROUP_FUNCTION_PRIVILEGE_MANAGER", "ejb/4TRESSUserGroupFunctionPrivilegeManager70");

    public static final String FTRESS_EJB_USER_GROUP_MANAGER = FtressConfiguration.getProperty(
            "JNDI_FTRESS_EJB_USER_GROUP_MANAGER", "ejb/4TRESSUserGroupManager70");

    public static final String FTRESS_EJB_USER_GROUP_TRANSACTION_AUTHORISATION_PRIVILEGE_MANAGER = FtressConfiguration
            .getProperty("JNDI_FTRESS_EJB_USER_GROUP_TRANSACTION_AUTHORISATION_PRIVILEGE_MANAGER",
                    "ejb/4TRESSUserGroupAuthorisationPrivilegeManager70");

    public static final String FTRESS_EJB_USER_TRANSACTION_AUTHORISATION_PRIVILEGE_MANAGER = FtressConfiguration
            .getProperty("JNDI_FTRESS_EJB_USER_TRANSACTION_AUTHORISATION_PRIVILEGE_MANAGER",
                    "ejb/4TRESSUserAuthorisationPrivilegeManager70");

    public static final String FTRESS_ADMIN_EJB = FtressConfiguration.getProperty("JNDI_FTRESS_ADMIN_EJB",
            "ejb/FtressAdministration70");
    
    public static final String FTRESS_EJB_AUTHORISATION_TRANSACTION_CONFIGURATION = FtressConfiguration.getProperty("JNDI_FTRESS_EJB_AUTHORISATION_TRANSACTION_CONFIGURATION",
    "ejb/4TRESSAuthorisationTransactionConfiguration70");
    
    public static final String FTRESS_EJB_AUTHORISOR = FtressConfiguration.getProperty("JNDI_FTRESS_EJB_AUTHORISOR",
    "ejb/4TRESSAuthorisor70");
    

    /**
     * Not used.
     */
    private FtressServiceFactory() {

        super();
    }
    public final static Authorisor getAuthorisor() throws RemoteException, InternalException{
    	AuthorisorHome factory = null;

        try {
            factory = (AuthorisorHome) FtressServiceLocator.getInstance().lookupRemoteHome(
            		FTRESS_EJB_AUTHORISOR, AuthorisorHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + AuthorisorHome.class.getName());
        }
    }
    public final static AuthorisationTransactionConfiguration getAuthorisationTransactionConfiguration() throws RemoteException, InternalException{
    	AuthorisationTransactionConfigurationHome factory = null;

        try {
            factory = (AuthorisationTransactionConfigurationHome) FtressServiceLocator.getInstance().lookupRemoteHome(
            		FTRESS_EJB_AUTHORISATION_TRANSACTION_CONFIGURATION, AuthorisationTransactionConfigurationHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + AuthorisationTransactionConfigurationHome.class.getName());
        }
    }
    public final static AssetAuthorisor getAssetAuthorisorEJB() throws InternalException, RemoteException {

        AssetAuthorisorHome factory = null;

        try {
            factory = (AssetAuthorisorHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_ASSET_TRANSACTION_AUTHORISOR, AssetAuthorisorHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + AssetAuthorisorHome.class.getName());
        }
    }

    public final static AssetManager getAssetManagerEJB() throws InternalException, RemoteException {

        AssetManagerHome factory = null;

        try {
            factory = (AssetManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(FTRESS_EJB_ASSET_MANAGER,
                    AssetManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + AssetManagerHome.class.getName());
        }
    }

    public final static Auditor getAuditorEJB() throws InternalException, RemoteException {

        AuditorHome factory = null;

        try {
            factory = (AuditorHome) FtressServiceLocator.getInstance().lookupRemoteHome(FTRESS_EJB_AUDITOR,
                    AuditorHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + AuditorHome.class.getName());
        }
    }

    public final static Authenticator getAuthenticatorEJB() throws InternalException, RemoteException {

        AuthenticatorHome factory = null;

        try {
            factory = (AuthenticatorHome) FtressServiceLocator.getInstance().lookupRemoteHome(FTRESS_EJB_AUTHENTICATOR,
                    AuthenticatorHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + AuthenticatorHome.class.getName());
        }
    }

    public final static AuthenticatorConfiguration getAuthenticatorConfigurationEJB() throws InternalException,
            RemoteException {

        AuthenticatorConfigurationHome factory = null;

        try {
            factory = (AuthenticatorConfigurationHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_AUTHENTICATOR_CONFIGURATION, AuthenticatorConfigurationHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + AuthenticatorConfigurationHome.class.getName());
        }
    }

    public final static AuthenticatorManager getAuthenticatorManagerEJB() throws InternalException, RemoteException {

        AuthenticatorManagerHome factory = null;

        try {
            factory = (AuthenticatorManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_AUTHENTICATOR_MANAGER, AuthenticatorManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + AuthenticatorManagerHome.class.getName());
        }
    }

    public final static BatchProcessor getBatchProcessorEJB() throws InternalException, RemoteException {

        BatchProcessorHome factory = null;

        try {
            factory = (BatchProcessorHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_BATCH_PROCESSOR, BatchProcessorHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + BatchProcessorHome.class.getName());
        }
    }

    public final static CredentialManager getCredentialManagerEJB() throws InternalException, RemoteException {

        CredentialManagerHome factory = null;

        try {
            factory = (CredentialManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_CREDENTIAL_MANAGER, CredentialManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + CredentialManagerHome.class.getName());
        }
    }

    public final static DeviceConfiguration getDeviceConfigurationEJB() throws InternalException, RemoteException {

        DeviceConfigurationHome factory = null;

        try {
            factory = (DeviceConfigurationHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_DEVICE_CONFIGURATION, DeviceConfigurationHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + DeviceConfigurationHome.class.getName());
        }
    }

    public final static DeviceManager getDeviceManagerEJB() throws InternalException, RemoteException {

        DeviceManagerHome factory = null;

        try {
            factory = (DeviceManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_DEVICE_MANAGER, DeviceManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + DeviceManagerHome.class.getName());
        }
    }
/*
    public final static FormManager getFormManagerEJB() throws InternalException, RemoteException {

        FormManagerHome factory = null;

        try {
            factory = (FormManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(FTRESS_EJB_FORM_MANAGER,
                    FormManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + FormManagerHome.class.getName());
        }
    }
*/
    public final static FunctionManager getFunctionManagerEJB() throws InternalException, RemoteException {

        FunctionManagerHome factory = null;

        try {
            factory = (FunctionManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_FUNCTION_MANAGER, FunctionManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + FunctionManagerHome.class.getName());
        }
    }

    public final static PrincipalConfiguration getPrincipalConfigurationEJB() throws InternalException, RemoteException {

        PrincipalConfigurationHome factory = null;

        try {
            factory = (PrincipalConfigurationHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_PRINCIPAL_CONFIGURATION, PrincipalConfigurationHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + PrincipalConfigurationHome.class.getName());
        }
    }

    public final static RoleAssetPrivilegeManager getRoleAssetPrivilegeManagerEJB() throws InternalException,
            RemoteException {

        RoleAssetPrivilegeManagerHome factory = null;

        try {
            factory = (RoleAssetPrivilegeManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_ROLE_ASSET_PRIVILEGE_MANAGER, RoleAssetPrivilegeManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + RoleAssetPrivilegeManagerHome.class.getName());
        }
    }

    public final static RoleManager getRoleManagerEJB() throws InternalException, RemoteException {

        RoleManagerHome factory = null;

        try {
            factory = (RoleManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(FTRESS_EJB_ROLE_MANAGER,
                    RoleManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + RoleManagerHome.class.getName());
        }
    }

    public final static RoleFunctionPrivilegeManager getRoleFunctionPrivilegeManagerEJB() throws InternalException,
            RemoteException {

        RoleFunctionPrivilegeManagerHome factory = null;

        try {
            factory = (RoleFunctionPrivilegeManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_ROLE_FUNCTION_PRIVILEGE_MANAGER, RoleFunctionPrivilegeManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + RoleFunctionPrivilegeManagerHome.class.getName());
        }
    }

    public final static RoleAuthorisationPrivilegeManager getRoleTransactionAuthorisationPrivilegeManagerEJB()
            throws InternalException, RemoteException {

        RoleAuthorisationPrivilegeManagerHome factory = null;

        try {
            factory = (RoleAuthorisationPrivilegeManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_ROLE_TRANSACTION_AUTHORISATION_PRIVILEGE_MANAGER,
                    RoleAuthorisationPrivilegeManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + RoleAuthorisationPrivilegeManagerHome.class.getName());
        }
    }

    public final static SessionTransfer getSessionTransferEJB() throws InternalException, RemoteException {

        SessionTransferHome factory = null;

        try {
            factory = (SessionTransferHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_SESSION_TRANSFER, SessionTransferHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + SessionTransferHome.class.getName());
        }
    }

    public final static SystemConfiguration getSystemConfigurationEJB() throws InternalException, RemoteException {

        SystemConfigurationHome factory = null;

        try {
            factory = (SystemConfigurationHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_SYSTEM_CONFIGURATION, SystemConfigurationHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + SystemConfigurationHome.class.getName());
        }
    }

    public final static AuthorisationTransactionConfiguration getTransactionAuthorisationConfigurationEJB()
            throws InternalException, RemoteException {

        AuthorisationTransactionConfigurationHome factory = null;

        try {
            factory = (AuthorisationTransactionConfigurationHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_TRANSACTION_AUTHORISATION_CONFIGURATION, AuthorisationTransactionConfigurationHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + AuthorisationTransactionConfigurationHome.class.getName());
        }
    }

    public final static AuthorisationTransactionManager getTransactionAuthorisationManagerEJB()
            throws InternalException, RemoteException {

        AuthorisationTransactionManagerHome factory = null;

        try {
            factory = (AuthorisationTransactionManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_TRANSACTION_AUTHORISATION_MANAGER, AuthorisationTransactionManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + AuthorisationTransactionManagerHome.class.getName());
        }
    }

    public final static Authorisor getAuthorisorEJB() throws InternalException, RemoteException {

        AuthorisorHome factory = null;

        try {
            factory = (AuthorisorHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_TRANSACTION_AUTHORISOR, AuthorisorHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + AuthorisorHome.class.getName());
        }
    }

    public final static UserAssetPrivilegeManager getUserAssetPrivilegeManagerEJB() throws InternalException,
            RemoteException {

        UserAssetPrivilegeManagerHome factory = null;

        try {
            factory = (UserAssetPrivilegeManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_USER_ASSET_PRIVILEGE_MANAGER, UserAssetPrivilegeManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + UserAssetPrivilegeManagerHome.class.getName());
        }
    }

    public final static UserFunctionPrivilegeManager getUserFunctionPrivilegeManagerEJB() throws InternalException,
            RemoteException {

        UserFunctionPrivilegeManagerHome factory = null;

        try {
            factory = (UserFunctionPrivilegeManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_USER_FUNCTION_PRIVILEGE_MANAGER, UserFunctionPrivilegeManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + UserFunctionPrivilegeManagerHome.class.getName());
        }
    }

    public final static UserGroupAssetPrivilegeManager getUserGroupAssetPrivilegeManagerEJB() throws InternalException,
            RemoteException {

        UserGroupAssetPrivilegeManagerHome factory = null;

        try {
            factory = (UserGroupAssetPrivilegeManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_USER_GROUP_ASSET_PRIVILEGE_MANAGER, UserGroupAssetPrivilegeManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + UserGroupAssetPrivilegeManagerHome.class.getName());
        }
    }

    public final static UserGroupFunctionPrivilegeManager getUserGroupFunctionPrivilegeManagerEJB()
            throws InternalException, RemoteException {

        UserGroupFunctionPrivilegeManagerHome factory = null;

        try {
            factory = (UserGroupFunctionPrivilegeManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_USER_GROUP_FUNCTION_PRIVILEGE_MANAGER, UserGroupFunctionPrivilegeManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + UserGroupFunctionPrivilegeManagerHome.class.getName());
        }
    }

    public final static UserGroupAuthorisationPrivilegeManager getUserGroupTransactionAuthorisationPrivilegeManagerEJB()
            throws InternalException, RemoteException {

        UserGroupAuthorisationPrivilegeManagerHome factory = null;

        try {
            factory = (UserGroupAuthorisationPrivilegeManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_USER_GROUP_TRANSACTION_AUTHORISATION_PRIVILEGE_MANAGER,
                    UserGroupAuthorisationPrivilegeManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + UserGroupAuthorisationPrivilegeManagerHome.class.getName());
        }
    }

    public final static UserManager getUserManagerEJB() throws InternalException, RemoteException {

        UserManagerHome factory = null;

        try {
            factory = (UserManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(FTRESS_EJB_USER_MANAGER,
                    UserManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + UserManagerHome.class.getName());
        }
    }

    public final static UserGroupManager getUserGroupManagerEJB() throws InternalException, RemoteException {

        UserGroupManagerHome factory = null;

        try {
            factory = (UserGroupManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_USER_GROUP_MANAGER, UserGroupManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + UserGroupManagerHome.class.getName());
        }
    }

    public final static UserAuthorisationPrivilegeManager getUserTransactionAuthorisationPrivilegeManagerEJB()
            throws InternalException, RemoteException {

        UserAuthorisationPrivilegeManagerHome factory = null;

        try {
            factory = (UserAuthorisationPrivilegeManagerHome) FtressServiceLocator.getInstance().lookupRemoteHome(
                    FTRESS_EJB_USER_TRANSACTION_AUTHORISATION_PRIVILEGE_MANAGER,
                    UserAuthorisationPrivilegeManagerHome.class);
            return factory.create();
        } catch (CreateException e) {
            throw new InternalException("Failed to create remote reference using factory: "
                    + UserAuthorisationPrivilegeManagerHome.class.getName());
        }
    }
}
