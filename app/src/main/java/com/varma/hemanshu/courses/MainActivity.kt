package com.varma.hemanshu.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.varma.hemanshu.courses.data.DataSource
import com.varma.hemanshu.courses.model.Topic
import com.varma.hemanshu.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    CoursesApp(DataSource.topics)
                }
            }
        }
    }
}

@Composable
fun CoursesApp(courses: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(courses) { course ->
            TopicItem(topic = course, modifier = modifier)
        }
    }
}

@Composable
fun TopicItem(topic: Topic, modifier: Modifier = Modifier) {
    Card {
        Row {
            Image(
                modifier = modifier.size(68.dp),
                painter = painterResource(id = topic.image),
                contentDescription = stringResource(id = topic.name)
            )
            Column(modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                Text(
                    text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = modifier.size(8.dp))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = stringResource(
                            id = topic.name
                        )
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    Text(
                        modifier = modifier.align(Alignment.CenterVertically),
                        text = topic.numberOfCourses.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopicItemPreview() {
    CoursesTheme {
        TopicItem(
            Topic(R.string.architecture, 58, R.drawable.architecture)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CoursesPreview() {
    CoursesTheme {
        CoursesApp(DataSource.topics)
    }
}