package com.revature.data;

import java.util.Set;

import com.revature.beans.Bear;

public interface BearDao {
	Bear addBear(Bear b);
	Bear getBearById(int i);
	Set<Bear> getBears();
	Bear updateBear(Bear b);
	void deleteBear(Bear b);
}
