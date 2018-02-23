package com.calvo.carolina.business.interactors.getallactivities

import android.content.Context
import com.calvo.carolina.business.mappers.map
import com.calvo.carolina.business.model.Places
import com.calvo.carolina.repository.Repository
import com.calvo.carolina.repository.RepositoryObjectInjector
import com.calvo.carolina.repository.models.ActivityEntity
import com.calvo.carolina.util.ErrorClosure
import com.calvo.carolina.util.SuccessClosure
import java.lang.ref.WeakReference

class GetAllActivitiesInteractorImpl(weakContext: WeakReference<Context>): GetAllActivitiesInteractor
{
     val repository: Repository =  RepositoryObjectInjector(weakContext).buildRepository()

    override fun execute(isSpanish: Boolean, success: SuccessClosure<Places>, error: ErrorClosure)
    {
        repository.getAllActivities(
                success = {activitiesEntityList: List<ActivityEntity> ->
                    val activities = Places(activitiesEntityList.map { it.map(isSpanish) }.toMutableList())
                    success(activities)
                },
                error = {
                    error(it)
                })
    }
}