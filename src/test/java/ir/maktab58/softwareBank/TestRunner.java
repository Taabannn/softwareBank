package ir.maktab58.softwareBank;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

/**
 * @author Taban Soleymani
 */

@RunWith(JUnitPlatform.class)
@SelectPackages({"ir.maktab58.service",
                 "ir.maktab58.models",
                 "ir.maktab58.models.eventsfactory",
                 "ir.maktab58.view"})
@IncludeClassNamePatterns({"^.*XTests?$"})
public class TestRunner {

}
