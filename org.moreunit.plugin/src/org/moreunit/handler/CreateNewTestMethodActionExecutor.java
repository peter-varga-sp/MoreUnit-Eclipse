package org.moreunit.handler;

import org.eclipse.jdt.core.ICompilationUnit;
import org.moreunit.elements.TestmethodCreator.TestMethodCreationSettings;
import org.moreunit.preferences.Preferences;
import org.moreunit.preferences.Preferences.ProjectPreferences;
import org.moreunit.ui.EditorUI;

public class CreateNewTestMethodActionExecutor extends CreateTestMethodActionExecutor
{

    private static CreateNewTestMethodActionExecutor instance;

    private CreateNewTestMethodActionExecutor()
    {
        this(new EditorUI(), Preferences.getInstance());
    }

    CreateNewTestMethodActionExecutor(EditorUI editorUI, Preferences preferences)
    {
        super(editorUI, preferences);
    }

    public static CreateNewTestMethodActionExecutor getInstance()
    {
        if(instance == null)
        {
            instance = new CreateNewTestMethodActionExecutor();
        }
        return instance;
    }

    TestMethodCreationSettings createTestMethodCreationSettings(ICompilationUnit compilationUnit, CreationContext context, ProjectPreferences prefs)
    {
        TestMethodCreationSettings settings = super.createTestMethodCreationSettings(compilationUnit, context, prefs);
        settings.generateNextTestMethod(true);
        return settings;
    }

}
