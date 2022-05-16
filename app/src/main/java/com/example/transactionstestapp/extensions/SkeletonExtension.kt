package com.example.transactionstestapp.extensions

import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.transactionstestapp.R
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.SkeletonConfig
import com.faltenreich.skeletonlayout.applySkeleton

fun RecyclerView.applyDefaultSkeleton(
    @LayoutRes listItemLayoutResId: Int,
    itemCount: Int = 6
): Skeleton {
    val config = SkeletonConfig.default(context).apply {
        maskColor = ContextCompat.getColor(context, R.color.skeleton)
        shimmerColor = ContextCompat.getColor(context, R.color.skeleton_50)
        shimmerDurationInMillis = 1500
        maskCornerRadius = context.resources.getDimension(R.dimen.skeleton_corner_radius)
    }
    return applySkeleton(listItemLayoutResId, itemCount, config)
}

fun Skeleton.toggle(showSkeleton: Boolean) {
    if (showSkeleton) showSkeleton()
    else showOriginal()
}