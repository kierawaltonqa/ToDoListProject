INSERT INTO TO_DO_LISTS_DOMAIN (TITLE)
VALUES
('General Tasks'),
('Shopping List'),
('QA Project 2 tasks');

INSERT INTO TO_DO_ENTRIES_DOMAIN (DESCRIPTION,DUE_DATE, COMPLETED, MY_LIST_ID)
VALUES
('create back end','2021-02-06',true,3),
('testing for back end','2021-02-08',false,3),
('create front end','2021-02-09',false,3),
('buy dad a birthday present','2021-02-07',true,1),
('send dbs check back to qa','2021-02-06',false,1);