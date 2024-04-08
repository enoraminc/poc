package com.example.demo;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class UserService {

    public String createUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture = dbFirestore.collection("user").document(user.getDocumentId()).set(user);
        return apiFuture.get().getUpdateTime().toString();
    }

    public User getUser(String documentId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("user").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        User user = null;
        try {
            DocumentSnapshot documentSnapshot = future.get();
            if (documentSnapshot.exists()) {
                user = documentSnapshot.toObject(User.class);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return user;
    }

}
