/*
 * Copyright 2008-2010 Xebia and the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package fr.xebia.demo.amazon.aws.petclinic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.xebia.demo.amazon.aws.petclinic.InfrastructureJCloudsMakerAnswer;
import fr.xebia.demo.amazon.aws.petclinic.AbstractInfrastructureMaker;

public class InfrastructureJCloudsMakerTest {
    private AbstractInfrastructureMaker maker;
    
    @Before
    public void setup() {
        System.out.println();
        maker = new InfrastructureJCloudsMakerAnswer();
    }

    @Test
    public void test_create_dbinstances() {
        Assert.assertNotNull("No DB Instance available",
                maker.createDBInstanceAndWaitForAvailability("petclinic-"+PersonalConfig.TRIGRAM));
    }

    @Test
    public void test_create_ec2instances_and_tag() {
        Assert.assertNotNull("No EC2 Instance created",
                maker.terminateExistingAndCreateNewInstance(PersonalConfig.TRIGRAM));
    }

    @Test
    public void test_create_elb() {
        Assert.assertNotNull("No ELB Instance created",
                maker.createElasticLoadBalancer(PersonalConfig.TRIGRAM));
    }

}