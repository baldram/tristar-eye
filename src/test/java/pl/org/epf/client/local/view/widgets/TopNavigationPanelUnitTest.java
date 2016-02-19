package pl.org.epf.client.local.view.widgets;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import com.google.gwt.event.dom.client.ClickEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.jboss.errai.GwtMockitoRunnerExtension;

import pl.org.epf.client.local.event.PageChange;
import pl.org.epf.client.local.view.FavouritesViewModel;
import pl.org.epf.client.local.view.MapTabViewModel;

import javax.enterprise.event.Event;

@RunWith(GwtMockitoRunnerExtension.class)
public class TopNavigationPanelUnitTest {

  @InjectMocks
  private TopNavigationPanel testableInstance;

  @Mock
  private Event<PageChange> pageChangeEvent;

  @Captor
  private ArgumentCaptor<PageChange> pageChangeCaptor;

  private ClickEvent clickEvent = null;

  @Test
  public void requestSwitchToFavourites() throws Exception {
    testableInstance.switchToFavourites(clickEvent);

    verify(pageChangeEvent).fire(pageChangeCaptor.capture());
    assertThat(pageChangeCaptor.getValue().getPageName(), is(equalTo(FavouritesViewModel.PAGE_NAME)));
  }

  @Test
  public void requestSwitchToMap() throws Exception {
    testableInstance.switchToMap(clickEvent);

    verify(pageChangeEvent).fire(pageChangeCaptor.capture());
    assertThat(pageChangeCaptor.getValue().getPageName(), is(equalTo(MapTabViewModel.PAGE_NAME)));
  }

}
