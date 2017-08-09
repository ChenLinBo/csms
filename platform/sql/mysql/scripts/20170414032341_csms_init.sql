--// csms_init
-- Migration SQL that makes the change goes here.

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (3, '�참���ܿ�', 'MODULE', 'BUSINESS', '/overviews.htm', NULL, 3);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (4, '�����ල', 'MODULE', 'BUSINESS', '/caseWarnings.htm', NULL, 4);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (5, '¼��¼��', 'MODULE', 'BUSINESS', '/caseMedias.htm', NULL, 5);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (7, '��������', 'MODULE', 'BUSINESS', '/bdmPoliceStations.htm', NULL, 7);

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (31, '�������', 'GROUP', 'BUSINESS', NULL, 3, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (311, '�참�����', 'LEAF', 'BUSINESS', '/overviews.htm', 31, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (312, '�참���澯', 'LEAF', 'BUSINESS', '/bdmAlarm.htm', 31, 2);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (32, '��������', 'GROUP', 'BUSINESS', NULL, 3, 2);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (321, '��������', 'LEAF', 'BUSINESS', '/cases.htm', 32, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (33, '��������', 'GROUP', 'BUSINESS', NULL, 3, 3);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (331, '�ֳ�����', 'LEAF', 'BUSINESS', '/cases/dispute.htm', 33, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (332, '��������', 'LEAF', 'BUSINESS', '/cases/civil.htm', 33, 2);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (333, '���°���', 'LEAF', 'BUSINESS', '/cases/criminal.htm', 33, 3);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (334, '�참��¼', 'LEAF', 'BUSINESS', '/caseRecords.htm', 33, 4);

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (41, '���ֹ���', 'GROUP', 'BUSINESS', NULL, 4, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (411, '���۴��', 'LEAF', 'BUSINESS', '/caseScores/mark.htm', 41, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (412, '�ҵĻ���', 'LEAF', 'BUSINESS', '/caseScores/detail.htm', 41, 2);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (413, '���۱�׼', 'LEAF', 'BUSINESS', '/bdmEvaluations.htm', 41, 3);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (42, '��������', 'GROUP', 'BUSINESS', NULL, 4, 2);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (421, '��λ��������', 'LEAF', 'BUSINESS', '/caseScores/rank/bdmPoliceStationRank.htm', 42, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (422, '�񾯻�������', 'LEAF', 'BUSINESS', '/caseScores/rank/bdmPoliceRank.htm', 42, 2);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (423, '������������', 'LEAF', 'BUSINESS', '/caseScores/rank/caseRank.htm', 42, 3);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (43, '���յȼ�', 'LEAF', 'BUSINESS', '/bdmClassifications.htm', 4, 3);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (44, '�������ල', 'LEAF', 'BUSINESS', '/caseWarnings.htm', 4, 4);

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (51, '��Ƶ�б�', 'LEAF', 'BUSINESS', '/caseMedias.htm', 5, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (52, '��Ƶ���', 'LEAF', 'BUSINESS', '/bdmVideoCategories.htm', 5, 2);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (53, 'ͳ�Ʒ���', 'GROUP', 'BUSINESS', NULL, 5, 3);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (531, '��Ա��������', 'LEAF', 'BUSINESS', '/statis/pc_count.htm', 53, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (532, '��λ��������', 'LEAF', 'BUSINESS', '/statis/uc_count.htm', 53, 2);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (533, '��Ա��Ƶ����', 'LEAF', 'BUSINESS', '/statis/pm_count.htm', 53, 3);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (534, '��λ��Ƶ����', 'LEAF', 'BUSINESS', '/statis/um_count.htm', 53, 4);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (535, '������������', 'LEAF', 'BUSINESS', '/statis/c_trend.htm', 53, 5);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (536, '��Ƶ��������', 'LEAF', 'BUSINESS', '/statis/m_trend.htm', 53, 6);

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (71, '��������', 'LEAF', 'BUSINESS', '/bdmPoliceStations.htm', 7, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (72, '�참��ά��', 'LEAF', 'BUSINESS', '/bdmHandlingAreas.htm', 7, 2);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (73, '����ά��', 'LEAF', 'BUSINESS', '/bdmRooms.htm', 7, 3);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (74, '���䲼��', 'LEAF', 'BUSINESS', '/bdmRoomLayout.htm', 7, 4);

--//@UNDO
-- SQL to undo the change goes here.


