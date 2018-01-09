library("rJava")
library("KoNLP")
library("wordcloud")
library("RColorBrewer")
useSejongDic()  # 사전등록

DF <- read.csv('/Users/Enirobot/SenierProject/Web_scraping/news.csv', stringsAsFactors = FALSE)
data <- sapply(DF["title"], extractNoun, USE.NAMES = F)              # 명사

data_unlist <- unlist(data)                                          # 리스트 -> 벡터로 바꿈

wordcount <- table(data_unlist)                                      # 빈도수 출력을 위해 table로 바꿈

wordcount_top <-head(sort(wordcount, decreasing = T),100)            # 최대 빈도 상위 100개

wordcloud(names(wordcount_top), wordcount_top, family="AppleGothic") # family="AppleGothic"안쓰면 한글 깨짐
