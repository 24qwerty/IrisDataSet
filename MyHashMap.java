public class MyHashMap {
	private int size;
	private int itemsInMyHashMap;
	HashMapItem[] hashMap;
	MyHashMap(int size){
		this.size=size;
		itemsInMyHashMap=0;
		hashMap=new HashMapItem[size];
		for(int i=0;i<size;i++){
			hashMap[i]=null;
		}
	}
	boolean set(String key,Object value){
		//Seperate chaining is used to avoid collision
		int i=(key.hashCode())%size;
		HashMapItem first=hashMap[i];
		HashMapItem previous;
		if(first==null){						//When no items at hashMap[i]
			hashMap[i]=new HashMapItem(key,value);
			itemsInMyHashMap++;
		}
		else{									//When there exist items at hashMap[i]
			previous=null;
			boolean oldkey=false;
			while(first!=null){					//If the key is repeated,replace old value by new value
				if(first.getKey()==key){
					oldkey=true;
					if(previous==null){
						hashMap[i]=new HashMapItem(key,value,first.getNextItem());
					}
					else{
						HashMapItem newItem=new HashMapItem(key, value, first.getNextItem());
						previous.nextItem=newItem;
					}
				}
				previous=first;
				first=first.getNextItem();
			}
			if(!oldkey){						//If the key is not repeated,add item at the end of the list of hashMap[i]
				HashMapItem newItem=new HashMapItem(key, value, null);
				previous.setNextItem(newItem);
				itemsInMyHashMap++;
			}
		}
		return true;
	}
	Object get(String key){
		int i=(key.hashCode())%size;
		HashMapItem first=hashMap[i];
		if(first==null){						//If no item with such key value exist in the hash map[i]
			return null;
		}
		while(first!=null){						
			if(first.key==key){
				return first;
			}
			first=first.getNextItem();
		}
		return null;
	}
	Object delete(String key){
		int i=(key.hashCode())%size;
		HashMapItem first=hashMap[i];
		HashMapItem previous=null;
		if(first==null){						//If no item with such key value exist in the hash map
			return null;
		}
		while(first!=null){
			if(first.key==key){
				itemsInMyHashMap--;
				if(previous==null){
					if(first.getNextItem()==null){	//If item is the only item at hashMap[i]
						hashMap[i]=null;
					}
					else{							//If item is the first item at hashMap[i]
						hashMap[i]=first.getNextItem();
					}
				}
				else{								//If item is the not the first item at hashMap[i]
					previous.setNextItem(first.getNextItem());
				}
				return first;
			}
			previous=first;
			first=first.getNextItem();
		}
		return null;
	}
	float load(){									//load factor of the hash map
		float loadFactor=(float)(itemsInMyHashMap)/(float)(size);
		return loadFactor;
	}
	class HashMapItem{
		private String key;
		private Object value;
		private HashMapItem nextItem;
		HashMapItem(String key,Object value){
			this.key=key;
			this.value=value;
			nextItem=null;
		}
		HashMapItem(String key,Object value,HashMapItem nextItem){
			this.key=key;
			this.value=value;
			this.nextItem=nextItem;
		}
		public HashMapItem getNextItem(){
			return nextItem;
		}
		public void setNextItem(HashMapItem nextItem){
			this.nextItem=nextItem;
		}
		public String getKey(){
			return key;
		}
		public Object getValue(){
			return value;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			MyHashMap hashMap1=new MyHashMap(10);
			//inserting into hash map
			boolean inserted=hashMap1.set("1", "A");
			System.out.println("inserted item 1 :"+inserted);
			inserted=hashMap1.set("8", "Aaaaaaaa");
			System.out.println("inserted item 2 :"+inserted);
			inserted=hashMap1.set("am", "AA");
			System.out.println("inserted item 3 :"+inserted);
			//load factor of the hash map
			float loadFactor=hashMap1.load();
			System.out.println("\nload factor="+loadFactor);
			//retrieving item from hash map
			HashMapItem item=(HashMapItem) hashMap1.get("am");
			System.out.println("\nretrieved item :key="+item.getKey()+ " value="+item.getValue());
			//deleting from hash map
			HashMapItem deleted=(HashMapItem) hashMap1.delete("8");
			System.out.println("\ndeleted item :key="+deleted.getKey()+ " value="+deleted.getValue());
			deleted=(HashMapItem) hashMap1.delete("am");
			System.out.println("deleted item :key="+deleted.getKey()+ " value="+deleted.getValue());
	}
}
