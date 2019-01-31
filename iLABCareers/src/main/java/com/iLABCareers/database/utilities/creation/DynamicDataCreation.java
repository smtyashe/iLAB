package com.iLABCareers.database.utilities.creation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.iLABCareers.database.entities.BaseEntity;

public class DynamicDataCreation extends DataCreation {
	private Random random;
	private PositiveData positiveData;
	private static int MAX = 20;

	public DynamicDataCreation() {
		super();
		random = new Random();
		positiveData = new PositiveData();
	}


	@Override
	protected List<BaseEntity> getEmployerEntityData() {
		List<BaseEntity> entities = new ArrayList<BaseEntity>();
		return entities;
	}

	@Override
	protected List<BaseEntity> getSearchEntityData() {
		List<BaseEntity> entities = new ArrayList<BaseEntity>();
		
		return entities;
	}

	protected class PositiveData {
		String getSurname() {
			String[] lastNames = { "Botha", "Stevens", "Mogwera", "Setati", "Vermeulen", "Miya", "Smith", "Dlamini",
					"Buthelezi", "Mwamba" };
			return getRandomItem(lastNames);
		}

		String getPassword() {
			String[] passwords = { "Botha123", "Stevens123", "123Mogwera" };
			return getRandomItem(passwords);
		}

		String getFirstName() {
			String[] firstNames = { "Vincent", "Tapiwa", "Joseph", "Thabiso", "Tebogo", "Unathi", "Maria", "Cheryl",
					"Martha", "Mandisa" };
			return getRandomItem(firstNames);
		}

		String getContact() {
			String[] contactNumber = { "834579654", "845685463", "7418654128", "825468412", "784569231", "824698146",
					"793625147", "4511215467", "545336154", "781546891" };
			return getRandomItem(contactNumber);
		}

		String getGender() {
			String[] gender = { "Male", "Female" };
			return getRandomItem(gender);
		}

		String getProvince() {
			String[] location = { "Eldoret", "Kisumu", "Mombasa", "Nairobi", "Nakuru", "Thikz", "Trans Nzoia",
					"Rest Of Kenya", "Outside Lenya" };
			return getRandomItem(location);
		}

		String getCountryDialingCode() {
			String[] dialingCodes = { "+27", "+254", "+250", "+255", "+256", "+234" };
			return getRandomItem(dialingCodes);
		}

		String getCoutryNameCode() {
			String[] contryName = { "ZA", "LS" };
			return getRandomItem(contryName);
		}

		String getMessage() {
			String[] messages = { "Message 1", "message 2" };
			return getRandomItem(messages);
		}

		String getPosition() {
			String[] positions = { "Entry Level", "Manager" };
			return getRandomItem(positions);
		}

		String getOrganisation() {
			String[] positions = { "Brighter Monday", "CrystalQA" };
			return getRandomItem(positions);
		}

		String getSearchCategory() {
			String[] categories = { "Accounting & Auditing", "Administrative", "Art & Design",
					"Building & Architecture", "Consulting & Strategy", "Customer Service & Support", "Engineering",
					"Farming", "Food Services", "IT & Telecoms", "Legal", "Management",
					"Marketing &amp; Communications", "Medicine & Pharmaceutical", "Project Management",
					"Property Management", "Quality Control & Assurance ", "Recruitment", "Research",
					"Sales & Business Development", "Social Developement", "Supply Chain & Procurement",
					"Teaching & Training", "Trades & Services" };
			return getRandomItem(categories);
		}

		String getSearchQuery() {
			String[] queryText = { "books", "laptop", "car", "truck" };
			return getRandomItem(queryText);
		}

		String getYear() {
			String year = Integer.toString(1920 + random.nextInt(80));
			return year;
		}

		String getMonth() {
			String month = Integer.toString(1 + random.nextInt(12));
			return month;
		}

		String getDay() {
			String day = Integer.toString(1 + random.nextInt(31));
			return day;
		}

		String getRandomItem(String[] items) {
			String randomItem = items[random.nextInt(items.length)];
			return randomItem;
		}
	}
}

