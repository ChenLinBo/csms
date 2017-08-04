package com.bycc.enumItem;

/**
 * ����ö��
 *
 */
public enum Nation {
	HAN("01", "��"), MENGGU("02", "�ɹ�"), HUI("03", "��"), ZANG("04", "��"), WEIWUER("05", "ά���"), MIAO("06", "��"),
	YI("07", "����"), ZHUANG("08", "׳��"), BUYI("09", "������"), CHAOXIAN("10", "������"), MAN("11", "����"), DONG("12", "����"), YAO("13", "����")
	, BAI("14", "����"), TUJIA("15", "������"), HANI("16", "������"), HASAKE("17", "��������"), DAI("18", "����"), LI("19", "����"), SULI("20", "������")
	, WA("21", "����")
	, YU("22", "����")
	, GAOSHAN("23", "��ɽ��")
	, LAHU("24", "������")
	, SHUI("25", "ˮ��")
	, DONGXIANG("26", "������")
	, NAXI("27", "������")
	, JINGPO("28", "������")
	, KEERKEZI("29", "�¶�������")
	, TU("30", "����")
	, DAWOER("31", "���Ӷ���")
	, MULAO("32", "������")
	, QIANG("33", "Ǽ��")
	, BULANG("34", "������")
	, SALA("35", "������")
	, MAONAN("36", "ë����")
	, GELAO("37", "������")
	, XIBO("38", "������")
	, ACHANG("39", "������")
	, PUMI("40", "������")
	, TAJIKE("41", "��������")
	, NU("42", "ŭ��")
	, WUZIBIEKE("43", "���ȱ����")
	, ELUOSI("44", "����˹��")
	, EWENKE("45", "���¿���")
	, DEANG("46", "�°���")
	, BAOAN("47", "������")
	, YUGU("48", "ԣ����")
	, JING("49", "����")
	, TATAER("50", "��������")
	, DULONG("51", "������")
	, ELUNCHUN("52", "���״���")
	, HEZHE("53", "������")
	, MENBA("54", "�Ű���")
	, LUOBA("55", "�����")
	, JINUO("56", "��ŵ��")
	, OTHER("57", "����")
	, FOREIGN("58", "���Ѫͳ");

	// �������
	private String code;

	private String value;

	private Nation(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public static Nation getByCode(String code) {
		for(Nation nation: values()){
			if (nation.getCode().equals(code)) {
				return nation;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
