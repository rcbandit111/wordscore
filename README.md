# wordscore_engine

## Available running Jobs

1. Domain Com check
2. Domain Net check
3. Domain Org check
4. Words Count Job
5. Blacklist check job


## Delete table columns content

UPDATE processed_words SET seo_score_uk = NULL;

UPDATE processed_words SET seo_score_us = NULL;

UPDATE processed_words SET volume_us = NULL;

UPDATE processed_words SET high_range = NULL;

UPDATE processed_words SET low_range = NULL;
