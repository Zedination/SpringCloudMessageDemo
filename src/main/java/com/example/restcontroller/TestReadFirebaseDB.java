package com.example.restcontroller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.TokenObj;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.NonNull;

import ch.qos.logback.core.subst.Token;

@RestController
public class TestReadFirebaseDB {

	@GetMapping("test-realtime")
	public List<String> demo01() throws InterruptedException {
		List<String> data = new ArrayList<String>();
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
//		DatabaseReference ref = database.getReference("list_tokens");
//		System.out.println(ref.orderByChild("hoc_phi").equalTo(true));
		Query query = database.getReference("list-tokens").orderByChild("hoc_phi").equalTo(false);
		Query query1 = database.getReference("list-tokens").orderByKey();
		query.addValueEventListener(new ValueEventListener() {

			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				snapshot.getChildren().forEach(snap -> {
					System.out.println(snap.getKey());
					System.out.println(snap.getValue(TokenObj.class).toString());
					data.add(snap.getKey());
				});

			}
			@Override
			public void onCancelled(DatabaseError error) {
				// TODO Auto-generated method stub

			}
		});
		Thread.sleep(1000);
		System.out.println(data.size());
//		Query query3 = database.getReference("list-tokens/"+"1598122099749");
//		query3.addValueEventListener(new ValueEventListener() {
//
//			@Override
//			public void onDataChange(@NonNull DataSnapshot snapshot) {
//				System.out.println(snapshot.getChildrenCount());
//				snapshot.getChildren().forEach(snap -> {
//					System.out.println(snap.getKey());
//					System.out.println(snap.getValue(Boolean.class).toString());
//				});
//
//			}
//
//			@Override
//			public void onCancelled(DatabaseError error) {
//				// TODO Auto-generated method stub
//
//			}
//		});
		return null;
	}

	@GetMapping("write-db")
	public String demo02() {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		TokenObj obj = new TokenObj();
		obj.setHoc_phi(true);
		obj.setKhao_sat(false);
		obj.setLich_hoc(true);
		database.getReference("list-tokens").child(String.valueOf(System.currentTimeMillis())).setValueAsync(obj);
		return null;
	}

	@GetMapping("delete-db")
	public String demo03() {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		database.getReference("list-tokens").child("1598122098982").setValueAsync(null);
		return null;
	}
}
