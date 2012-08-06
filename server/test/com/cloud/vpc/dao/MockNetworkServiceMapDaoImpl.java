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
package com.cloud.vpc.dao;

import java.util.List;

import javax.ejb.Local;

import com.cloud.network.Network.Provider;
import com.cloud.network.Network.Service;
import com.cloud.network.NetworkServiceMapVO;
import com.cloud.network.dao.NetworkServiceMapDao;
import com.cloud.utils.db.DB;
import com.cloud.utils.db.GenericDaoBase;

@Local(value = NetworkServiceMapDao.class)
@DB(txn = false)
public class MockNetworkServiceMapDaoImpl extends GenericDaoBase<NetworkServiceMapVO, Long> implements NetworkServiceMapDao{

    /* (non-Javadoc)
     * @see com.cloud.network.dao.NetworkServiceMapDao#areServicesSupportedInNetwork(long, com.cloud.network.Network.Service[])
     */
    @Override
    public boolean areServicesSupportedInNetwork(long networkId, Service... services) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see com.cloud.network.dao.NetworkServiceMapDao#canProviderSupportServiceInNetwork(long, com.cloud.network.Network.Service, com.cloud.network.Network.Provider)
     */
    @Override
    public boolean canProviderSupportServiceInNetwork(long networkId, Service service, Provider provider) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see com.cloud.network.dao.NetworkServiceMapDao#getServicesInNetwork(long)
     */
    @Override
    public List<NetworkServiceMapVO> getServicesInNetwork(long networkId) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.cloud.network.dao.NetworkServiceMapDao#getProviderForServiceInNetwork(long, com.cloud.network.Network.Service)
     */
    @Override
    public String getProviderForServiceInNetwork(long networkid, Service service) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.cloud.network.dao.NetworkServiceMapDao#deleteByNetworkId(long)
     */
    @Override
    public void deleteByNetworkId(long networkId) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see com.cloud.network.dao.NetworkServiceMapDao#getDistinctProviders(long)
     */
    @Override
    public List<String> getDistinctProviders(long networkId) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.cloud.network.dao.NetworkServiceMapDao#isProviderForNetwork(long, com.cloud.network.Network.Provider)
     */
    @Override
    public String isProviderForNetwork(long networkId, Provider provider) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NetworkServiceMapVO findById(Long id) {
        NetworkServiceMapVO vo = null;
        if (id.longValue() == 1) {
            vo = new NetworkServiceMapVO(1, Service.SourceNat, Provider.VPCVirtualRouter);
        } else if (id.longValue() == 2) {
            vo = new NetworkServiceMapVO(2, Service.Firewall, Provider.VirtualRouter);
        } else if (id.longValue() == 3) {
            vo = new NetworkServiceMapVO(3, Service.SourceNat, Provider.VPCVirtualRouter);
        } else if (id.longValue() == 4) {
            vo = new NetworkServiceMapVO(4, Service.SourceNat, Provider.VPCVirtualRouter);
        }
        
        return vo;
    }
}
