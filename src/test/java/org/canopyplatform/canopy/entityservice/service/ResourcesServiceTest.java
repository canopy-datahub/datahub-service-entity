package org.canopyplatform.canopy.entityservice.service;

import org.canopyplatform.canopy.entityservice.mapper.*;

import org.canopyplatform.canopy.entityservice.model.*;
import org.canopyplatform.canopy.entityservice.model.DTO.*;
import org.canopyplatform.canopy.entityservice.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResourcesServiceTest {

    @Mock
    private EventRepository eventRepo;

    @Mock
    private NewsRepository newsRepo;

    @Mock
    private FundingRepository fundingRepository;
    @Mock
    private DataFileRepository dataFileRepository;

    @Mock
    private StudyRepository studyRepository;

    @Mock
    private DataSubmissionRepository dataSubmissionRepository;

    @Mock
    private NewsletterRepository newsletterRepository;

    @InjectMocks
    private ResourcesService service;

    @BeforeEach
    public void setup(){
        EventMapper eventMapper = Mappers.getMapper(EventMapper.class);
        NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);
        CenterStatsMapper statsMapper = Mappers.getMapper(CenterStatsMapper.class);
        HomepageContentMapper contentMapper = Mappers.getMapper(HomepageContentMapper.class);
        FundingMapper fundingMapper = Mappers.getMapper(FundingMapper.class);
        StudyMapper studyMapper = Mappers.getMapper(StudyMapper.class);
        NewsletterMapper newsletterMapper = Mappers.getMapper(NewsletterMapper.class);
        service = new ResourcesService(eventRepo, newsRepo, fundingRepository, studyRepository, dataSubmissionRepository,
                dataFileRepository, eventMapper, newsMapper, fundingMapper, studyMapper, statsMapper,
                contentMapper, newsletterRepository, newsletterMapper);
    }


    @Test
    void testGetEvents(){
        List<Event> eventList = new ArrayList<>();
        eventList.add(generateTestEvent());
        when(eventRepo.findEventsByEventDateAfterOrderByEventDateAsc(any(), any())).thenReturn(eventList);

        List<EventDTO> result = service.getEvents();

        //This doesn't really test anything substantial, don't rely on this alone
        Assertions.assertNotNull(result);
        //These will test the mapping worked as expected
        EventDTO e = result.get(0);
        Assertions.assertEquals(1, e.getId());
        Assertions.assertEquals("Test Title", e.getTitle());
        Assertions.assertEquals("Test Description....", e.getDescription());
        Assertions.assertEquals("event1", e.getSlug());
        Assertions.assertEquals("webinar", e.getType());
        Assertions.assertNull(e.getRegistrationUrl());
        Assertions.assertEquals(LocalDate.of(2024, Month.JUNE, 15).atStartOfDay(), e.getEventDate());
        Assertions.assertEquals(LocalDate.of(2023, Month.DECEMBER, 1).atStartOfDay(), e.getCreatedAt());
        Assertions.assertNull(e.getExpirationDate());
    }

    @Test
    void testGetAllEvents(){
        List<Event> eventList = new ArrayList<>();
        eventList.add(generateTestEvent());
        when(eventRepo.findEventsByEventDateAfterOrderByEventDateAsc(any(), any())).thenReturn(eventList);
        eventList = new ArrayList<>();
        eventList.add(generateTestEvent2());
        when(eventRepo.findAllByEventDateBeforeOrderByEventDateDesc(any())).thenReturn(eventList);

        CombinedEventsDTO result = service.getAllEvents();

        //This doesn't really test anything substantial, don't rely on this alone
        Assertions.assertNotNull(result);
        //These will test the mapping worked as expected
        EventDTO e = result.getCurrentEvents().get(0);

        Assertions.assertEquals(1, e.getId());
        Assertions.assertEquals("Test Title", e.getTitle());
        Assertions.assertEquals("Test Description. This line may or may not appear based on the test used.", e.getDescription());
        Assertions.assertEquals("event1", e.getSlug());
        Assertions.assertEquals("webinar", e.getType());
        Assertions.assertNull(e.getRegistrationUrl());
        Assertions.assertEquals(LocalDate.of(2024, Month.JUNE, 15).atStartOfDay(), e.getEventDate());
        Assertions.assertEquals(LocalDate.of(2023, Month.DECEMBER, 1).atStartOfDay(), e.getCreatedAt());
        Assertions.assertNull(e.getExpirationDate());

        EventDTO e2 = result.getPastEvents().get(0);
        Assertions.assertEquals(2, e2.getId());
        Assertions.assertEquals("Test Title2", e2.getTitle());
        Assertions.assertEquals("Test Description 2. This line may or may not appear based on the test used.", e2.getDescription());
        Assertions.assertEquals("event2", e2.getSlug());
        Assertions.assertEquals("webinar2", e2.getType());
        Assertions.assertNull(e2.getRegistrationUrl());
        Assertions.assertEquals(LocalDate.of(2024, Month.JUNE, 15).atStartOfDay(), e2.getEventDate());
        Assertions.assertEquals(LocalDate.of(2023, Month.DECEMBER, 1).atStartOfDay(), e2.getCreatedAt());
        Assertions.assertNull(e2.getExpirationDate());
    }


    @Test
    void testGetNews() throws ParseException {
        List<News> newsList = new ArrayList<>();
        newsList.add(generateTestNews());
        when(newsRepo.findByType_NameOrderByStartDateDesc(anyString())).thenReturn(newsList);

        List<NewsDTO> result = service.getNews();

        //This doesn't really test anything substantial, don't rely on this alone
        Assertions.assertNotNull(result);
        //These will test the mapping worked as expected
        NewsDTO n = result.get(0);
        Assertions.assertEquals(1, n.getId());
        Assertions.assertEquals("Test Title", n.getTitle());
        Assertions.assertEquals("Test Description....", n.getDescription());
        Assertions.assertEquals("Slug1", n.getSlug());
        Assertions.assertEquals("TestNewsType", n.getType());
        Assertions.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2024-06-15"), n.getStartDate());
        Assertions.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-15"), n.getExpirationDate());
        Assertions.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-15"), n.getCreatedAt());
    }


    @Test
    void testGetAllNews() throws ParseException {
        List<News> newsList = new ArrayList<>();
        newsList.add(generateTestNews());
        when(newsRepo.findByType_NameOrderByStartDateDesc(anyString())).thenReturn(newsList);

        List<NewsDTO> result = service.getAllNews();

        //This doesn't really test anything substantial, don't rely on this alone
        Assertions.assertNotNull(result);
        //These will test the mapping worked as expected
        NewsDTO n = result.get(0);
        Assertions.assertEquals(1, n.getId());
        Assertions.assertEquals("Test Title", n.getTitle());
        Assertions.assertEquals("Test Description. This line may or may not appear based on the test used.", n.getDescription());
        Assertions.assertEquals("Slug1", n.getSlug());
        Assertions.assertEquals("TestNewsType", n.getType());
        Assertions.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2024-06-15"), n.getStartDate());
        Assertions.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-15"), n.getExpirationDate());
        Assertions.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-15"), n.getCreatedAt());
    }

    @Test
    void testGetNewsletters(){
        List<Newsletter> mockNewsletters = getMockNewsletters();
        when(newsletterRepository.findAllByReleaseDateIsLessThanEqualOrderByReleaseDateDesc(any(LocalDate.class)))
                .thenReturn(mockNewsletters);
        Map<Integer, List<NewsletterDTO>> response = service.getNewsletters();

        Assertions.assertTrue(response.containsKey(2023));
        Assertions.assertTrue(response.containsKey(2024));
        Assertions.assertEquals("Newsletter 1", response.get(2023).get(0).title());
        Assertions.assertEquals("Newsletter 2", response.get(2024).get(0).title());
    }

    private List<Newsletter> getMockNewsletters(){
        Newsletter newsletter1 = new Newsletter();
        newsletter1.setId(1);
        newsletter1.setUrl("test.com/1");
        newsletter1.setReleaseDate(LocalDate.of(2023, 01, 01));
        newsletter1.setTitle("Newsletter 1");
        Newsletter newsletter2 = new Newsletter();
        newsletter2.setId(2);
        newsletter2.setUrl("test.com/2");
        newsletter2.setReleaseDate(LocalDate.of(2024, 01, 01));
        newsletter2.setTitle("Newsletter 2");
        return List.of(newsletter2, newsletter1);
    }

    private Event generateTestEvent(){
        EventType et = new EventType();
        et.setId(1);
        et.setName("webinar");
        Event event = new Event();
        event.setId(1);
        event.setTitle("Test Title");
        event.setSlug("event1");
        event.setDescription("Test Description. ||| This line may or may not appear based on the test used.");
        event.setEventType(et);
        event.setEventDate(LocalDate.of(2024, Month.JUNE, 15).atStartOfDay());
        event.setCreatedAt(LocalDate.of(2023, Month.DECEMBER, 1).atStartOfDay());
        event.setCreatedBy(9999);
        return event;
    }

    private Event generateTestEvent2(){
        EventType et = new EventType();
        et.setId(2);
        et.setName("webinar2");
        Event event = new Event();
        event.setId(2);
        event.setTitle("Test Title2");
        event.setSlug("event2");
        event.setDescription("Test Description 2. ||| This line may or may not appear based on the test used.");
        event.setEventType(et);
        event.setEventDate(LocalDate.of(2024, Month.JUNE, 15).atStartOfDay());
        event.setCreatedAt(LocalDate.of(2023, Month.DECEMBER, 1).atStartOfDay());
        event.setCreatedBy(9999);
        return event;
    }

    private News generateTestNews() throws ParseException {
        NewsType nt = new NewsType();
        nt.setId(1);
        nt.setName("TestNewsType");

        News news = new News();
        news.setId(1);
        news.setSlug("Slug1");
        news.setTitle("Test Title");
        news.setDescription("Test Description. ||| This line may or may not appear based on the test used.");
        news.setType(nt);
        news.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2024-06-15"));
        news.setExpirationDate(new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-15"));
        news.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-15"));
        news.setCreatedBy(9999);

        return news;
    }


    @Test
    void testGetAllCenterStats(){
        when(dataFileRepository.getCenterStats()).thenReturn(getListForCenterStatsTest());

        HomepageStatsDTO dto = service.getAllCenterStats();
        Assertions.assertEquals(5, dto.getTotalFiles().intValue());
        Assertions.assertEquals(3, dto.getTotalStudies().intValue());
        CenterDTO center = dto.getDtos().get(0);
        Assertions.assertEquals("testName", center.getName());
        Assertions.assertEquals(3, center.getStudyCount());
        Assertions.assertEquals(4, center.getDocumentCount().longValue());
        Assertions.assertEquals(5, center.getDataFileCount().longValue());
        Assertions.assertEquals(6, center.getTotalFileSize().intValue());
    }

    private List<Map<String, Object>> getListForCenterStatsTest(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "testName");
        map.put("study_count", 3L);
        map.put("document_count", BigDecimal.valueOf(4));
        map.put("data_file_count", BigDecimal.valueOf(5));
        map.put("total_file_size", BigDecimal.valueOf(6));
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        return list;
    }

    @Test
    void testGetHomepageContent(){
        when(studyRepository.findRecentlyApprovedStudies(any())).thenReturn(getNewStudyList());
        when(studyRepository.findFirstThreeRecentlyApprovedStudies()).thenReturn(getNewStudyList());
        when(dataFileRepository.findRecentNewFiles(anyInt(), any())).thenReturn(new ArrayList<>());
        when(dataFileRepository.findLatestNewFiles()).thenReturn(getNewFileList());
        when(dataFileRepository.findRecentUpdatedFiles(anyInt(), any())).thenReturn(new ArrayList<>());
        when(dataFileRepository.findLatestUpdatedFiles()).thenReturn(getUpdatedFileList());

        HomepageContentDTO result = service.getHomepageContent();
        Assertions.assertFalse(result.getNewFiles().isEmpty());
        Assertions.assertFalse(result.getNewStudies().isEmpty());
        Assertions.assertFalse(result.getUpdatedFiles().isEmpty());

        HomepageContentDataDTO newFile = result.getNewFiles().get(0);
        HomepageContentDataDTO updatedFile = result.getUpdatedFiles().get(0);
        HomepageContentDataDTO newStudy = result.getNewStudies().get(0);

        Assertions.assertEquals("Jan 16, 2023", newFile.getDate());
        Assertions.assertEquals(1, newFile.getFiles());
        Assertions.assertEquals("newFileDcc", newFile.getCenter());
        Assertions.assertEquals(2, newFile.getStudyId());
        Assertions.assertEquals("newFileStudyName", newFile.getStudyName());

        Assertions.assertEquals("Jan 17, 2023", updatedFile.getDate());
        Assertions.assertEquals(3, updatedFile.getFiles());
        Assertions.assertEquals("updatedFileDcc", updatedFile.getCenter());
        Assertions.assertEquals(4, updatedFile.getStudyId());
        Assertions.assertEquals("updatedFileStudyName", updatedFile.getStudyName());

        Assertions.assertEquals("Jan 18, 2023", newStudy.getDate());
        Assertions.assertNull(newStudy.getFiles());
        Assertions.assertEquals("newStudyDcc", newStudy.getCenter());
        Assertions.assertEquals(5, newStudy.getStudyId());
        Assertions.assertEquals("newStudyName", newStudy.getStudyName());
    }

    private List<Map<String, Object>> getNewFileList(){
        List<Map<String, Object>> newFileList = new ArrayList<>();
        Map<String, Object> newFileMap = new HashMap<>();
        newFileMap.put("files", 1L);
        newFileMap.put("center", "newFileDcc");
        newFileMap.put("study_id", 2);
        newFileMap.put("title", "newFileStudyName");
        newFileMap.put("date", Timestamp.valueOf("2023-01-16 00:00:00.0"));
        newFileList.add(newFileMap);
        return newFileList;
    }

    private List<Map<String, Object>> getUpdatedFileList(){
        List<Map<String, Object>> newFileList = new ArrayList<>();
        Map<String, Object> newFileMap = new HashMap<>();
        newFileMap.put("files", 3L);
        newFileMap.put("center", "updatedFileDcc");
        newFileMap.put("study_id", 4);
        newFileMap.put("title", "updatedFileStudyName");
        newFileMap.put("date", Timestamp.valueOf("2023-01-17 00:00:00.0"));
        newFileList.add(newFileMap);
        return newFileList;
    }

    private List<StudyView> getNewStudyList(){
        List<StudyView> newFileList = new ArrayList<>();
        StudyView newFileMap = new StudyView();
        newFileMap.setCenter("newStudyDcc");
        newFileMap.setId(5);
        newFileMap.setName("newStudyName");
        newFileMap.setReleaseDate("01/18/2023");
        newFileList.add(newFileMap);
        return newFileList;
    }
}
