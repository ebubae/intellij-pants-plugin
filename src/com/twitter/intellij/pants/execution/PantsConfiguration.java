/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * @author Jeka
 */
package com.twitter.intellij.pants.execution;

import com.intellij.debugger.engine.RemoteStateState;
import com.intellij.debugger.impl.GenericDebuggerRunnerSettings;
import com.intellij.debugger.settings.DebuggerSettings;
import com.intellij.diagnostic.logging.LogConfigurationPanel;
import com.intellij.execution.ExecutionBundle;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.RunConfigurationWithSuppressedDefaultRunAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.options.SettingsEditorGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class PantsConfiguration extends ModuleBasedConfiguration<JavaRunConfigurationModule> implements
                                                                                              RunConfigurationWithSuppressedDefaultRunAction {

  @Override
  public void writeExternal(final Element element) throws WriteExternalException {
    super.writeExternal(element);
    final Module module = getConfigurationModule().getModule();
    if (module != null) { // default value
      writeModule(element);
    }
    DefaultJDOMExternalizer.writeExternal(this, element);
  }

  @Override
  public void readExternal(final Element element) throws InvalidDataException {
    super.readExternal(element);
    readModule(element);
    DefaultJDOMExternalizer.readExternal(this, element);
  }

  public String WORKING_DIR;
  public String COMMAND_LINE;
  public String PANTS_EXE;

  public PantsConfiguration(final Project project, ConfigurationFactory configurationFactory) {
    super(new JavaRunConfigurationModule(project, true), configurationFactory);
  }

  @Override
  public RunProfileState getState(@NotNull final Executor executor, @NotNull final ExecutionEnvironment env) throws ExecutionException {
    GenericDebuggerRunnerSettings debuggerSettings = (GenericDebuggerRunnerSettings)env.getRunnerSettings();
    debuggerSettings.LOCAL = false;
//    debuggerSettings.setDebugPort(USE_SOCKET_TRANSPORT ? PANTS_EXE : WORKING_DIR);
//    debuggerSettings.setTransport(USE_SOCKET_TRANSPORT ? DebuggerSettings.SOCKET_TRANSPORT : DebuggerSettings.SHMEM_TRANSPORT);
    return new RemoteStateState(getProject(), null); // TODO pants
  }

  @Override
  @NotNull
  public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
    SettingsEditorGroup<PantsConfiguration> group = new SettingsEditorGroup<PantsConfiguration>();
    group.addEditor(ExecutionBundle.message("run.configuration.configuration.tab.title"), new PantsConfigurable(getProject()));
    group.addEditor(ExecutionBundle.message("logs.tab.title"), new LogConfigurationPanel<PantsConfiguration>());
    return group;
  }

  @Override
  public Collection<Module> getValidModules() {
    return getAllModules();
  }


}
