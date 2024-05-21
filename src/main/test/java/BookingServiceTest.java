import com.max.BookingService;
import com.max.CantBookException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookingServiceTest {
    private static final Logger logger =
            LoggerFactory.getLogger(BookingServiceTest.class);
    @Test
    public void checkTime() throws CantBookException {
        logger.info("Test checkTime is started");
        // given
        logger.debug("Composing spy for debug");
        BookingService spybookingService = spy(BookingService.class);
        logger.debug("Creating spy complete");
        // when
        Mockito.doReturn(true).when(spybookingService).checkTimeInBD(Mockito.any(LocalDateTime.class), any(LocalDateTime.class));
        boolean res = spybookingService.book("Stepan", LocalDateTime.now(), LocalDateTime.now());
        //then
        Assertions.assertEquals(false, res);
        verify(spybookingService, times(1)).book(anyString(),Mockito.any(LocalDateTime.class), any(LocalDateTime.class));
        logger.info("Test checkTime complete");

    }
    @Test
    public void checkBook() throws CantBookException {
        logger.info("Test checkBook is started");
        // given
        logger.debug("Composing spy for debug");
        BookingService spybookingService = spy(BookingService.class);
        logger.debug("Creating spy for debug complete");
        // when
        Mockito.doReturn(true).when(spybookingService).checkTimeInBD(Mockito.any(LocalDateTime.class), any(LocalDateTime.class));
        Mockito.doReturn(true).when(spybookingService).createBook(Mockito.anyString(), Mockito.any(LocalDateTime.class), any(LocalDateTime.class));
        boolean res = spybookingService.book("Stepan", LocalDateTime.now(), LocalDateTime.now());
        //then
        Assertions.assertEquals(true, res);
        verify(spybookingService, times(1)).createBook(anyString(),Mockito.any(LocalDateTime.class), any(LocalDateTime.class));
        logger.info("Test checkBook complete");
    }
    @Test
    public void checkCantBookException() throws CantBookException {
        logger.info("Test checkCantBookException is started");
        // given
        logger.debug("Composing spy for debug");
        BookingService spybookingService = spy(BookingService.class);
        logger.debug("Composing spy for debug complete");
        // when
        Mockito.doReturn(false).when(spybookingService).checkTimeInBD(Mockito.any(LocalDateTime.class), any(LocalDateTime.class));
        //then
        Assertions.assertThrows(CantBookException.class, () -> spybookingService.book("Stepan", LocalDateTime.now(), LocalDateTime.now()));
        verify(spybookingService, never()).createBook(anyString(),Mockito.any(LocalDateTime.class), any(LocalDateTime.class));
        logger.info("Test checkCantBookException complete");
    }

}
