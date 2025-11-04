import scrapy
import re
from scrapy.crawler import CrawlerProcess

class IndriveSpider(scrapy.Spider):
    
    name = 'indrive_formatted'
    allowed_domains = ['indrive.com']
    start_urls = ['https://indrive.com/']
    
    custom_settings = {
        'USER_AGENT': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36',
        'DOWNLOAD_DELAY': 1,
        'RANDOMIZE_DOWNLOAD_DELAY': True,
        'ROBOTSTXT_OBEY': False,
        'HTTPERROR_ALLOWED_CODES': [403],
    }

    def parse(self, response):
        if response.status == 403:
            self.logger.warning(f"Nhận được mã 403 Forbidden cho {response.url}.")
            if not response.text or len(response.text) < 100:
                self.logger.error(f"Status 403 : {response.url}")
                return

        emails = re.findall(r'[\w\.-]+@[\w\.-]+', response.text)

        links = [response.urljoin(href) for href in response.css('a::attr(href)').getall()]

        js_files = [response.urljoin(src) for src in response.css('script[src]::attr(src)').getall()]

        images = [response.urljoin(src) for src in response.css('img::attr(src)').getall()]
        
        videos = [response.urljoin(src) for src in response.css('video::attr(src), source::attr(src)').getall()]

        audio = [response.urljoin(src) for src in response.css('audio::attr(src), source::attr(src)').getall()]

        comments = response.xpath('//comment()').getall()

        yield {
            'url': response.url,
            'emails': list(set(emails)),
            'links': links,
            'js_files': js_files,
            'images': images,
            'videos': list(set(videos)),
            'audio': list(set(audio)),
            'comments': [comment.strip() for comment in comments]
        }

if __name__ == "__main__":
    process_settings = {
        'FEEDS': {
            'output_indriver_crawl.json': {
                'format': 'json',
                'overwrite': True,
                'encoding': 'utf8',
                'indent': 4,  
            },
        },
        'LOG_LEVEL': 'INFO',
    }

    process = CrawlerProcess(settings=process_settings)
    process.crawl(IndriveSpider)

    print("Crawling...")
    process.start()
    print("Out: 'output_indriver_crawl.json' ")