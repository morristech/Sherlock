package com.singhajit.sherlock.crashes;

import com.singhajit.sherlock.core.investigation.Crash;
import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.core.repo.CrashReports;
import com.singhajit.sherlock.crashes.action.CrashActions;
import com.singhajit.sherlock.crashes.presenter.CrashPresenter;
import com.singhajit.sherlock.crashes.viewmodel.AppInfoViewModel;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CrashPresenterTest {
  @Test
  public void shouldInitializeCrashView() throws Exception {
    CrashReports crashReports = mock(CrashReports.class);
    Crash crash = mock(Crash.class);
    when(crashReports.get(1)).thenReturn(crash);
    CrashActions actions = mock(CrashActions.class);
    CrashPresenter presenter = new CrashPresenter(crashReports, actions);

    CrashViewModel viewModel = mock(CrashViewModel.class);
    AppInfoViewModel appInfoViewModel = mock(AppInfoViewModel.class);
    when(viewModel.getAppInfoViewModel()).thenReturn(appInfoViewModel);
    presenter.render(1, viewModel);

    verify(viewModel).populate(crash);
    verify(actions).renderAppInfo(appInfoViewModel);
  }

  @Test
  public void shouldShareCrashDetails() throws Exception {
    CrashActions actions = mock(CrashActions.class);
    CrashPresenter presenter = new CrashPresenter(null, actions);

    CrashViewModel viewModel = mock(CrashViewModel.class);
    when(viewModel.getCrashInfo()).thenReturn("crashDetails");

    presenter.shareCrashDetails(viewModel);

    verify(actions).openSendApplicationChooser("crashDetails");
  }
}