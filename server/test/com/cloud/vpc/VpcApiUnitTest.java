// Copyright 2012 Citrix Systems, Inc. Licensed under the
// Apache License, Version 2.0 (the "License"); you may not use this
// file except in compliance with the License.  Citrix Systems, Inc.
// reserves all rights not expressly granted by the License.
// You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// 
// Automatically generated by addcopyright.py at 04/03/2012
package com.cloud.vpc;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Before;

import com.cloud.configuration.dao.ConfigurationDaoImpl;
import com.cloud.configuration.dao.ResourceCountDaoImpl;
import com.cloud.dc.dao.VlanDaoImpl;
import com.cloud.exception.InvalidParameterValueException;
import com.cloud.network.dao.FirewallRulesDaoImpl;
import com.cloud.network.dao.IPAddressDaoImpl;
import com.cloud.network.dao.NetworkDaoImpl;
import com.cloud.network.dao.PhysicalNetworkDaoImpl;
import com.cloud.network.vpc.VpcManager;
import com.cloud.network.vpc.VpcManagerImpl;
import com.cloud.network.vpc.Dao.PrivateIpDaoImpl;
import com.cloud.network.vpc.Dao.StaticRouteDaoImpl;
import com.cloud.network.vpc.Dao.VpcGatewayDaoImpl;
import com.cloud.network.vpc.Dao.VpcOfferingDaoImpl;
import com.cloud.network.vpc.Dao.VpcOfferingServiceMapDaoImpl;
import com.cloud.tags.dao.ResourceTagsDaoImpl;
import com.cloud.user.AccountVO;
import com.cloud.user.MockAccountManagerImpl;
import com.cloud.user.dao.AccountDaoImpl;
import com.cloud.utils.component.ComponentLocator;
import com.cloud.utils.component.MockComponentLocator;
import com.cloud.vm.dao.DomainRouterDaoImpl;
import com.cloud.vpc.dao.MockNetworkOfferingDaoImpl;
import com.cloud.vpc.dao.MockNetworkOfferingServiceMapDaoImpl;
import com.cloud.vpc.dao.MockNetworkServiceMapDaoImpl;
import com.cloud.vpc.dao.MockVpcDaoImpl;

public class VpcApiUnitTest extends TestCase{
    private static final Logger s_logger = Logger.getLogger(VpcApiUnitTest.class);
    MockComponentLocator _locator;
    VpcManager _vpcService;

    
    @Override
    @Before
    public void setUp() throws Exception {
        _locator = new MockComponentLocator("management-server");
        _locator.addDao("VpcDao", MockVpcDaoImpl.class);
        _locator.addDao("VpcOfferingDao", VpcOfferingDaoImpl.class);
        _locator.addDao("VpcOfferingServiceMapDao", VpcOfferingServiceMapDaoImpl.class);
        _locator.addDao("ConfigDao", ConfigurationDaoImpl.class);
        _locator.addDao("NetworkDao", NetworkDaoImpl.class);
        _locator.addDao("IPAddressDao", IPAddressDaoImpl.class);
        _locator.addDao("DomainRouterDao", DomainRouterDaoImpl.class);
        _locator.addDao("_vpcGatewayDao", VpcGatewayDaoImpl.class);
        _locator.addDao("PrivateIpDao", PrivateIpDaoImpl.class);
        _locator.addDao("StaticRouteDao", StaticRouteDaoImpl.class);
        _locator.addDao("NetworkOfferingServiceMapDao", MockNetworkOfferingServiceMapDaoImpl.class);
        _locator.addDao("VpcOfferingServiceMapDao", VpcOfferingServiceMapDaoImpl.class);
        _locator.addDao("PhysicalNetworkDao", PhysicalNetworkDaoImpl.class);
        _locator.addDao("ResourceTagDao", ResourceTagsDaoImpl.class);
        _locator.addDao("FirewallRulesDao", FirewallRulesDaoImpl.class);
        _locator.addDao("VlanDao", VlanDaoImpl.class);
        _locator.addDao("AccountDao", AccountDaoImpl.class);
        _locator.addDao("ResourceCountDao", ResourceCountDaoImpl.class);
        _locator.addDao("NetworkOfferingDao", MockNetworkOfferingDaoImpl.class);
        _locator.addDao("NetworkServiceMapDao", MockNetworkServiceMapDaoImpl.class);

        
        _locator.addManager("vpc manager", VpcManagerImpl.class);
        _locator.addManager("account manager", MockAccountManagerImpl.class);
        _locator.addManager("network manager", MockNetworkManagerImpl.class);
        _locator.addManager("Site2SiteVpnManager", MockSite2SiteVpnManagerImpl.class);
        _locator.addManager("ResourceLimitService", MockResourceLimitManagerImpl.class);
        _locator.addManager("ConfigService", MockConfigurationManagerImpl.class);
        _locator.makeActive(null);
        
        _vpcService = ComponentLocator.inject(VpcManagerImpl.class);
    }
    
    public void test() {
        //validate network offering
        //1) correct network offering
        boolean result = false;
        try {
            _vpcService.validateNtkwOffForVpc(1, "0.0.0.0", "111-", new AccountVO(), _vpcService.getVpc(1), 2L, "10.1.1.1");
            result = true;
            s_logger.debug("Test1 passed, the offering is valid for vpc creation");
        } catch (Exception ex) {
            s_logger.warn("Test1 failed due to exc ", ex);
        }
        
        
        //2) invalid offering - source nat is not included
        result = false;
        try {
            _vpcService.validateNtkwOffForVpc(2, "0.0.0.0", "111-", new AccountVO(), _vpcService.getVpc(1), 2L, "10.1.1.1");
            result = true;
        } catch (InvalidParameterValueException ex) {
        } finally {
            if (!result) {
                s_logger.debug("Test2 passed, can't use network offering without SourceNat service");
            } else {
                s_logger.warn("Test2 failed, can't use network offering without SourceNat service");
            }
        }
        
        //3) invalid offering - conserve mode is off
        result = false;
        try {
            _vpcService.validateNtkwOffForVpc(3, "0.0.0.0", "111-", new AccountVO(), _vpcService.getVpc(1), 2L, "10.1.1.1");
            result = true;
        } catch (InvalidParameterValueException ex) {
        } finally {
            if (!result) {
                s_logger.debug("Test3 passed, can't use network offering without conserve mode = true");
            } else {
                s_logger.warn("Test3 failed, can't use network offering without conserve mode = true");
            }
        }
        
       //3) invalid offering - guest type shared
        result = false;
        try {
            _vpcService.validateNtkwOffForVpc(4, "0.0.0.0", "111-", new AccountVO(), _vpcService.getVpc(1), 2L, "10.1.1.1");
            result = true;
        } catch (InvalidParameterValueException ex) { 
        } finally {
            if (!result) {
                s_logger.debug("Test4 passed, can't use network offering with guest type = Shared");
            } else {
                s_logger.warn("Test4 failed, can't use network offering with guest type = Shared");
            }
        }
    }

}
