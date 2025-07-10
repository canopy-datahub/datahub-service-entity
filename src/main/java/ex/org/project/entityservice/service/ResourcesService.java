package ex.org.project.entityservice.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import ex.org.project.entityservice.exception.ResourceNotFoundException;
import ex.org.project.entityservice.mapper.*;
import ex.org.project.entityservice.model.*;
import ex.org.project.entityservice.model.DTO.*;
import ex.org.project.entityservice.repository.*;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import static ex.org.project.entityservice.util.Constants.*;

@Service
@RequiredArgsConstructor
public class ResourcesService {
    private final EventRepository eventRepository;
    private final NewsRepository newsRepository;
    private final FundingRepository fundingRepository;
    private final StudyRepository studyRepository;
    private final DataSubmissionRepository dataSubmissionRepository;
    private final DataFileRepository dataFileRepository;
    private final EventMapper eventsMapper;
    private final NewsMapper newsMapper;
    private final FundingMapper fundingMapper;
    private final StudyMapper studyMapper;
    private final DccStatsMapper statsMapper;
    private final HomepageContentMapper homepageContentMapper;
    private final NewsletterRepository newsletterRepository;
    private final NewsletterMapper newsletterMapper;

    public List<EventDTO> getEvents() {
        List<Event> events = eventRepository.findEventsByEventDateAfterOrderByEventDateAsc(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS), Limit.of(3));
        return eventsMapper.eventsToHomepageDTOs(events);
    }

    public CombinedEventsDTO getAllEvents() {
        CombinedEventsDTO combinedEvents = new CombinedEventsDTO();

        List<Event> events = eventRepository.findEventsByEventDateAfterOrderByEventDateAsc(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS), Limit.unlimited());
        List<EventDTO> mappedEvents = eventsMapper.allToDTOs(events);
        combinedEvents.setCurrentEvents(mappedEvents);

        events = eventRepository.findAllByEventDateBeforeOrderByEventDateDesc(
                LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));
        mappedEvents = eventsMapper.allToDTOs(events);
        combinedEvents.setPastEvents(mappedEvents);

        return combinedEvents;
    }

    public List<NewsDTO> getNews() {    	
        List<News> news = newsRepository.findByType_NameOrderByStartDateDesc(NEWS_GENERAL);
        List<NewsDTO> generalNews = newsMapper.newsToHomepageDTOs(news);
        return generalNews;
    }

    public List<NewsDTO> getAllNews() {
        List<News> news = newsRepository.findByType_NameOrderByStartDateDesc(NEWS_GENERAL);
        List<NewsDTO> sortedNews = newsMapper.newsToAllNewsDTOs(news);
        return sortedNews;
    }

    public NewsDTO getNewsBySlug(String newsSlug) {
        News newsEntry = newsRepository.findBySlug(newsSlug)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("News entry not found for \"%s\"", newsSlug)));
        return newsMapper.newsToNewsDTO(newsEntry);
    }

    public List<FundingDTO> getFunding() {
        List<Funding> fundings = fundingRepository.findByExpirationDateGreaterThanEqualOrderByStartDateDesc(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));
        return fundingMapper.fundingToAllFundingDTOs(fundings);
    }

    public List<FundingDTO> getAllFunding(){
        List<Funding> fundings = fundingRepository.findAllByOrderByStartDateDesc();
        List<FundingDTO> fundingOpportunities = fundingMapper.fundingToAllFundingDTOs(fundings);
        return fundingOpportunities;
    }

    public List<StudyViewDTO> getRecentlyApprovedStudies() {
        //Get 30 days ago date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date date30DaysAgo = calendar.getTime();

        //Get list of study ids and convert to list of Longs
        List<Integer> studyList = dataSubmissionRepository.findUniqueStudyIdsLast30DaysOrRecent3(date30DaysAgo);

        // Return list of unique studies that were updated in last 30 days
        return studyMapper.toStudyDTOs(studyRepository.findByIdIn(studyList));
    }

    public HomepageStatsDTO getAllDccStats() {
        List<Map<String, Object>> dataFileDTOs = dataFileRepository.getDccStats();
        HomepageStatsDTO stats = new HomepageStatsDTO();
        List<DccDTO> dtos = statsMapper.mapToDccDtoList(dataFileDTOs);
        Long totalStudies = dtos.stream().mapToLong(DccDTO::getStudyCount).sum();
        Long totalFiles = dtos.stream().mapToLong(dccDTO -> dccDTO.getDataFileCount().longValue()).sum();
        stats.setTotalStudies(totalStudies);
        stats.setTotalFiles(totalFiles);
        stats.setDtos(dtos);
        return stats;
    }

    public HomepageContentDTO getHomepageContent() {

        HomepageContentDTO dto = new HomepageContentDTO();
        LocalDateTime timePeriod = LocalDateTime.now().minusDays(90);

        //Get new studies and put them into DTO
        //release date is a string in db so use the string value of time period
        List<StudyView> studyViews = studyRepository.findRecentlyApprovedStudies(timePeriod);
        if(studyViews.size() < 3) {
            studyViews = studyRepository.findFirstThreeRecentlyApprovedStudies();
        }
        List<HomepageContentDataDTO> newStudiesDTOS = homepageContentMapper.mapToDataDTOs(studyViews);
        dto.setNewStudies(newStudiesDTOS);

        //Get new files and put them into DTO
        List<Map<String, Object>> newFiles = dataFileRepository.findRecentNewFiles(1, timePeriod);
        if(newFiles.size() < 3) {
            newFiles = dataFileRepository.findLatestNewFiles();
        }
        dto.setNewFiles(homepageContentMapper.mapToDataDtoList(newFiles));

        //Get updated files and put them into DTO
        List<Map<String, Object>> updatedFiles = dataFileRepository.findRecentUpdatedFiles(1, timePeriod);
        if(updatedFiles.size() < 3) {
            updatedFiles = dataFileRepository.findLatestUpdatedFiles();
        }
        dto.setUpdatedFiles(homepageContentMapper.mapToDataDtoList(updatedFiles));

        return dto;
    }

    public Map<Integer, List<NewsletterDTO>> getNewsletters(){
        LocalDate today = LocalDate.now();
        List<Newsletter> newsletters = newsletterRepository.findAllByReleaseDateIsLessThanEqualOrderByReleaseDateDesc(today);
        List<NewsletterDTO> newsletterDTOS = newsletterMapper.mapEntitiesToDtoList(newsletters);
        return newsletterDTOS.stream()
                .collect(Collectors.groupingBy(newsletter -> newsletter.releaseDate().getYear(),
                                               Collectors.mapping(Function.identity(), Collectors.toList())));
    }

}