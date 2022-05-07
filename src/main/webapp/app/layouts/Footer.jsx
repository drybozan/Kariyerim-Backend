import React from "react";
import {
  Container,
  Grid,
  Header,
  Icon,
  List,
  Segment,
} from "semantic-ui-react";

export default function Footer() {
  return (
    <div>
      <Segment
        color="grey"
        inverted
        vertical
        style={{
          padding: "5em 0em",
          position: "static",
          bottom: 0,
          width: "100%",
          marginTop: "2em"
        }}
      >
        <Container>
          <Grid divided inverted stackable >
            <Grid.Row>
              <Grid.Column width={3}>
                <List link inverted>
                  <List.Item as="a">Hakkımızda</List.Item>
                  <List.Item as="a">İletişim</List.Item>
                  <List.Item as="a">Çerezler</List.Item>
                  <List.Item as="a">Gizlilik Politikası</List.Item>
                </List>
              </Grid.Column>
              <Grid.Column width={13}>
                <Header style={{ marginTop: "-1.2em" }} as="h2">
                  <Container>
                    <Icon name="graduation cap" color="black" size="big" />
                  </Container>
                  <Header.Content>
                    <font color="#f5f5f5">
                    KARİYERİM
                    </font>
                  </Header.Content>
                </Header>
                <Container>
                  © 2021 db System - Tüm hakları gizlidir
                </Container>
              </Grid.Column>
            </Grid.Row>
          </Grid>
        </Container>
      </Segment>
    </div>
  );
}
