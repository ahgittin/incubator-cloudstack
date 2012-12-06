// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package org.apache.cloudstack.api.user.address.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.apache.cloudstack.api.ApiConstants;
import org.apache.cloudstack.api.BaseListTaggedResourcesCmd;
import org.apache.cloudstack.api.IdentityMapper;
import org.apache.cloudstack.api.Implementation;
import org.apache.cloudstack.api.Parameter;
import com.cloud.api.response.IPAddressResponse;
import com.cloud.api.response.ListResponse;
import com.cloud.async.AsyncJob;
import com.cloud.network.IpAddress;
import com.cloud.utils.Pair;


@Implementation(description="Lists all public ip addresses", responseObject=IPAddressResponse.class)
public class ListPublicIpAddressesCmd extends BaseListTaggedResourcesCmd {
    public static final Logger s_logger = Logger.getLogger(ListPublicIpAddressesCmd.class.getName());

    private static final String s_name = "listpublicipaddressesresponse";

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////

    @Parameter(name=ApiConstants.ALLOCATED_ONLY, type=CommandType.BOOLEAN, description="limits search results to allocated public IP addresses")
    private Boolean allocatedOnly;

    @Parameter(name=ApiConstants.FOR_VIRTUAL_NETWORK, type=CommandType.BOOLEAN, description="the virtual network for the IP address")
    private Boolean forVirtualNetwork;

    @IdentityMapper(entityTableName="user_ip_address")
    @Parameter(name=ApiConstants.ID, type=CommandType.LONG, description="lists ip address by id")
    private Long id;

    @Parameter(name=ApiConstants.IP_ADDRESS, type=CommandType.STRING, description="lists the specified IP address")
    private String ipAddress;

    @IdentityMapper(entityTableName="vlan")
    @Parameter(name=ApiConstants.VLAN_ID, type=CommandType.LONG, description="lists all public IP addresses by VLAN ID")
    private Long vlanId;

    @IdentityMapper(entityTableName="data_center")
    @Parameter(name=ApiConstants.ZONE_ID, type=CommandType.LONG, description="lists all public IP addresses by Zone ID")
    private Long zoneId;

    @Parameter(name=ApiConstants.FOR_LOAD_BALANCING, type=CommandType.BOOLEAN, description="list only ips used for load balancing")
    private Boolean forLoadBalancing;

    @IdentityMapper(entityTableName="physical_network")
    @Parameter(name=ApiConstants.PHYSICAL_NETWORK_ID, type=CommandType.LONG, description="lists all public IP addresses by physical network id")
    private Long physicalNetworkId;

    @IdentityMapper(entityTableName="networks")
    @Parameter(name=ApiConstants.ASSOCIATED_NETWORK_ID, type=CommandType.LONG, description="lists all public IP addresses associated to the network specified")
    private Long associatedNetworkId;

    @Parameter(name=ApiConstants.IS_SOURCE_NAT, type=CommandType.BOOLEAN, description="list only source nat ip addresses")
    private Boolean isSourceNat;

    @Parameter(name=ApiConstants.IS_STATIC_NAT, type=CommandType.BOOLEAN, description="list only static nat ip addresses")
    private Boolean isStaticNat;

    @IdentityMapper(entityTableName="vpc")
    @Parameter(name=ApiConstants.VPC_ID, type=CommandType.LONG, description="List ips belonging to the VPC")
    private Long vpcId;

    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////
    public Long getId() {
        return id;
    }

    public Boolean isAllocatedOnly() {
        return allocatedOnly;
    }

    public Boolean isForVirtualNetwork() {
        return forVirtualNetwork;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Long getVlanId() {
        return vlanId;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public Long getPhysicalNetworkId() {
        return physicalNetworkId;
    }

    public Long getAssociatedNetworkId() {
        return associatedNetworkId;
    }

    public Boolean getIsSourceNat() {
        return isSourceNat;
    }

    public Boolean getIsStaticNat() {
        return isStaticNat;
    }

    public Long getVpcId() {
        return vpcId;
    }

    /////////////////////////////////////////////////////
    /////////////// API Implementation///////////////////
    /////////////////////////////////////////////////////
    @Override
    public String getCommandName() {
        return s_name;
    }

    @Override
    public void execute(){
        Pair<List<? extends IpAddress>, Integer> result = _mgr.searchForIPAddresses(this);
        ListResponse<IPAddressResponse> response = new ListResponse<IPAddressResponse>();
        List<IPAddressResponse> ipAddrResponses = new ArrayList<IPAddressResponse>();
        for (IpAddress ipAddress : result.first()) {
            IPAddressResponse ipResponse = _responseGenerator.createIPAddressResponse(ipAddress);
            ipResponse.setObjectName("publicipaddress");
            ipAddrResponses.add(ipResponse);
        }

        response.setResponses(ipAddrResponses, result.second());
        response.setResponseName(getCommandName());
        this.setResponseObject(response);
    }

    public AsyncJob.Type getInstanceType() {
        return AsyncJob.Type.IpAddress;
    }


    public Boolean isForLoadBalancing() {
        return forLoadBalancing;
    }

    public Boolean getAllocatedOnly() {
        return allocatedOnly;
    }

    public Boolean getForVirtualNetwork() {
        return forVirtualNetwork;
    }

    public Boolean getForLoadBalancing() {
        return forLoadBalancing;
    }
}