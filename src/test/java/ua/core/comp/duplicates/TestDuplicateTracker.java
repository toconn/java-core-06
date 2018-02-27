package ua.core.comp.duplicates;

import org.junit.*;

import ua.core.data.NameValuePair;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

public class TestDuplicateTracker {
	
	private final static String ITEM_1_NAME = "Item 1";
	private final static String ITEM_1_1_VALUE = "Item 1.1 Value";
	private final static String ITEM_1_2_VALUE = "Item 1.2 Value";
	private final static String ITEM_1_3_VALUE = "Item 1.3 Value";
	
	private final static String ITEM_2_NAME = "Item 2";
	private final static String ITEM_2_VALUE = "Item 2 Value";
	
	// private final static String ITEM_3_NAME = "Item 3";
	// private final static String ITEM_3_VALUE = "Item 3 Value";
	
	private final static NameValuePair ITEM_1_1 = new NameValuePair(ITEM_1_NAME, ITEM_1_1_VALUE);
	private final static NameValuePair ITEM_1_2 = new NameValuePair(ITEM_1_NAME, ITEM_1_2_VALUE);
	private final static NameValuePair ITEM_1_3 = new NameValuePair(ITEM_1_NAME, ITEM_1_3_VALUE);
	private final static NameValuePair ITEM_2 = new NameValuePair(ITEM_2_NAME, ITEM_2_VALUE);
	// private final static NameValuePair ITEM_3 = new NameValuePair(ITEM_3_NAME, ITEM_3_VALUE);
	
	DuplicateTracker <NameValuePair> emptyDuplicateTracker;
	DuplicateTracker <NameValuePair> singleItemDuplicateTracker;

	@Before
	public void init() {

		emptyDuplicateTracker = new DefaultDuplicateTracker<>();
		
		singleItemDuplicateTracker = new DefaultDuplicateTracker<>();
		singleItemDuplicateTracker.add(ITEM_1_NAME, ITEM_1_1);
	}
	
	@Test
	public void addingItemToEmptyTrackerDoesNotCreateDuplicate() {
		
		this.emptyDuplicateTracker.add(ITEM_1_NAME, ITEM_1_1);
		assertFalse (this.emptyDuplicateTracker.hasDuplicates());
	}
	
	@Test
	public void addingMatchingItemToTrackerCreatesDuplicate() {
		
		this.singleItemDuplicateTracker.add(ITEM_1_NAME, ITEM_1_1);
		assertTrue (this.singleItemDuplicateTracker.hasDuplicates());
	}

	@Test
	public void addingNonMatchingItemToTrackerDoesNotCreateDuplicate() {
		
		this.singleItemDuplicateTracker.add(ITEM_2_NAME, ITEM_2);
		assertFalse (this.singleItemDuplicateTracker.hasDuplicates());
	}
	
	@Test
	public void addingThreeMatchingItemsReturnsDuplicateListOfThreeItems() {
		
		Map<String, List<NameValuePair>> duplicateItemsMap;
		List<NameValuePair> duplicateItems;
		
		this.emptyDuplicateTracker.add(ITEM_1_NAME, ITEM_1_1);
		this.emptyDuplicateTracker.add(ITEM_1_NAME, ITEM_1_2);
		this.emptyDuplicateTracker.add(ITEM_1_NAME, ITEM_1_3);
		
		duplicateItemsMap = this.emptyDuplicateTracker.getDuplicatesMap();
		duplicateItems = duplicateItemsMap.get(ITEM_1_NAME);
		
		assertEquals (duplicateItemsMap.size(), 1);
		assertEquals (duplicateItems.size(), 3);
		assertTrue (duplicateItems.contains(ITEM_1_1));
		assertTrue (duplicateItems.contains(ITEM_1_2));
		assertTrue (duplicateItems.contains(ITEM_1_3));
	}
	
	@Test
	public void itemIsNotADuplicateInEmptyTracker() {
		
		assertFalse (this.emptyDuplicateTracker.isDuplicate(ITEM_1_NAME));
		assertTrue (this.emptyDuplicateTracker.isNotDuplicate(ITEM_1_NAME));
	}

	@Test
	public void matchingItemIsADuplicate() {
		
		assertTrue (this.singleItemDuplicateTracker.isDuplicate(ITEM_1_NAME));
		assertFalse (this.singleItemDuplicateTracker.isNotDuplicate(ITEM_1_NAME));
	}

}
