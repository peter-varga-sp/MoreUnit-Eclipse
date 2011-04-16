package org.moreunit.mock.utils;

/**
 * This class helps managing "conditional" features, i.e. features that are not
 * automatically active, for instance because they are still in development.
 */
public class Features
{
    public static final String NEW_TEST_CASE_WIZARD_DEPENDENCIES_PAGE = "org.moreunit.mock.feature.newTestCaseWizardDependenciesPage";

    public static boolean isActive(String feature)
    {
        return Boolean.valueOf(System.getProperty(feature, "false"));
    }
}
