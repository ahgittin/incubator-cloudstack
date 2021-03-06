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
package com.cloud.api.response;

import com.cloud.api.ApiConstants;
import com.cloud.utils.IdentityProxy;
import com.cloud.hypervisor.Hypervisor.HypervisorType;
import com.cloud.serializer.Param;
import com.google.gson.annotations.SerializedName;

public class HypervisorCapabilitiesResponse extends BaseResponse {
    @SerializedName(ApiConstants.ID) @Param(description="the ID of the hypervisor capabilities row")
    private IdentityProxy id = new IdentityProxy("hypervisor_capabilities");

    @SerializedName(ApiConstants.HYPERVISOR_VERSION) @Param(description="the hypervisor version")
    private String hypervisorVersion;

    @SerializedName(ApiConstants.HYPERVISOR) @Param(description="the hypervisor type")
    private HypervisorType hypervisor;

    @SerializedName(ApiConstants.MAX_GUESTS_LIMIT) @Param(description="the maximum number of guest vms recommended for this hypervisor")
    private Long maxGuestsLimit;

    @SerializedName(ApiConstants.SECURITY_GROUP_EANBLED) @Param(description="true if security group is supported")
    private boolean isSecurityGroupEnabled;

    @Override
    public Long getObjectId() {
        return getId();
    }

    public Long getId() {
        return id.getValue();
    }

    public void setId(Long id) {
        this.id.setValue(id);
    }


    public String getHypervisorVersion() {
        return hypervisorVersion;
    }

    public void setHypervisorVersion(String hypervisorVersion) {
        this.hypervisorVersion = hypervisorVersion;
    }

    public HypervisorType getHypervisor() {
        return hypervisor;
    }

    public void setHypervisor(HypervisorType hypervisor) {
        this.hypervisor = hypervisor;
    }

    public Long getMaxGuestsLimit() {
        return maxGuestsLimit;
    }

    public void setMaxGuestsLimit(Long maxGuestsLimit) {
        this.maxGuestsLimit = maxGuestsLimit;
    }

    public Boolean getIsSecurityGroupEnabled() {
        return this.isSecurityGroupEnabled;
    }

    public void setIsSecurityGroupEnabled(Boolean sgEnabled) {
        this.isSecurityGroupEnabled = sgEnabled;
    }
}
