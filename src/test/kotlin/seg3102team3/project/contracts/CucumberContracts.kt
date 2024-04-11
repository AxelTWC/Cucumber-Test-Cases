package seg3102team3.project.contracts

import io.cucumber.junit.platform.engine.Constants
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite

@Suite
@SelectClasspathResource("seg3102team3/project/contracts")
@ConfigurationParameter(
    key = Constants.GLUE_PROPERTY_NAME,
    value = "seg3102.project.contracts"
)
class CucumberContracts {
}
