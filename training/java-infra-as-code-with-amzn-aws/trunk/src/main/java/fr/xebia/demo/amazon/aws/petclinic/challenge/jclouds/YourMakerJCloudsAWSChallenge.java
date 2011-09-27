package fr.xebia.demo.amazon.aws.petclinic.challenge.jclouds;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.rds.model.DBInstance;
import com.google.common.base.Throwables;
import fr.xebia.demo.amazon.aws.petclinic.CloudInit;
import fr.xebia.demo.amazon.aws.petclinic.JCloudUtil;
import fr.xebia.demo.amazon.aws.petclinic.challenge.MakerChallengeAnswer;

import org.apache.commons.lang.NotImplementedException;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.compute.domain.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Use JCloud with Amazon AWS template Option class
 * see http://code.google.com/p/jclouds/wiki/ComputeGuide
 */
public class YourMakerJCloudsAWSChallenge extends MakerChallengeAnswer {
    private static final Logger LOGGER = LoggerFactory.getLogger(YourMakerJCloudsAWSChallenge.class);

    @Nonnull
    @Override
    /**
     * Create two EC2 instances using JClouds and AWS Template class
     * Create 1 context and 1 service then create two instances
     * The two JClouds instances are returned as EC2 instances
     */
    public List<Instance> createTwoEC2Instances(CloudInit cloudInit, DBInstance dbInstance, String warUrl) {
        ComputeServiceContext context = null;
        try {
            context = createComputeServiceContext();
            Template template = createDefaultTemplate(context, cloudInit, dbInstance, warUrl);
            LOGGER.debug("Request creation of 2 Ec2 instances for group {}",getGroup());
            Set<? extends NodeMetadata> nodes = context.getComputeService()
                    .createNodesInGroup(getGroup(), 2, template);
            return JCloudUtil.nodeMetadataAsEC2Instances(nodes);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        } finally {
            if (context != null) {
                context.close();
            }
        }
    }

    @Nonnull
    @Override
    public String getTrigram() {
        throw new NotImplementedException("TODO");
    }

    /**
     * @return The name of the instances
     */
    private String getGroup() {
        return "JClouds-" + getTrigram();
    }

    private ComputeServiceContext createComputeServiceContext()
            throws IOException {
        throw new NotImplementedException("TODO");
    }

    private Template createDefaultTemplate(ComputeServiceContext context, CloudInit cloudInit,
            DBInstance dbInstance, String warUrl) {
        throw new NotImplementedException("TODO");
    }
}
