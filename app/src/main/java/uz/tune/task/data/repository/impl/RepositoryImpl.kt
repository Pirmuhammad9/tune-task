package uz.tune.task.data.repository.impl

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.tune.task.data.model.CardData
import uz.tune.task.data.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val db: FirebaseFirestore) : Repository {
    override fun getList(): Flow<List<CardData>> = callbackFlow {
        db.collection("cards").get().addOnSuccessListener { querySnapshot ->
            val response = querySnapshot.map { documentSnapshot ->
                documentSnapshot.toObject(CardData::class.java)
            }
            trySend(response.sortedBy { it.currentTime }.reversed()).onFailure {
                trySend(emptyList())
            }
        }.addOnFailureListener {
            trySend(emptyList())
        }
        awaitClose { }
    }

    override fun addCard(cardData: CardData):Flow<Boolean> = callbackFlow {
        db.collection("cards").add(cardData).addOnSuccessListener {
            trySend(true)
        }.addOnFailureListener{
            trySend(false)
        }
        awaitClose {  }
    }
}