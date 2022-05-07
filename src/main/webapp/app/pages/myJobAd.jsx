import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import {
  Table,
  Button,
  Header,
  Icon,

} from "semantic-ui-react";


export default function MyJobAd() {

  let [jobAds, setJobAds] = useState([]);


  useEffect(() => {
   
   
  }, []);






  return (
    <div>   

      <Header  as="h2">
        <Icon name="bullhorn" />
        <Header.Content>İş İlanlarım</Header.Content>
      </Header>

      <Table  color="black" celled>
        <Table.Header>
          <Table.Row>
            <Table.HeaderCell>Şirket Adı</Table.HeaderCell>
            <Table.HeaderCell>İş Pozisyonu</Table.HeaderCell>
            <Table.HeaderCell>Şehir</Table.HeaderCell>
            <Table.HeaderCell>Maaş Aralığı</Table.HeaderCell>
            <Table.HeaderCell>Çalışma Zamanı</Table.HeaderCell>
            <Table.HeaderCell>Çalışma Yeri</Table.HeaderCell>
            <Table.HeaderCell>Son Tarih</Table.HeaderCell>
            <Table.HeaderCell>Başvuranlar</Table.HeaderCell>
           
          </Table.Row>
        </Table.Header>

        <Table.Body>
          {jobAds?.map((jobAd) => (
            <Table.Row key={jobAd.id}>
              <Table.Cell>{jobAd.employer.companyName}</Table.Cell>
              <Table.Cell>{jobAd.jobPosition.name}</Table.Cell>
              <Table.Cell>{jobAd.city.name}</Table.Cell>
              <Table.Cell>{jobAd.minSalary}₺ - {jobAd.maxSalary}₺</Table.Cell>
              <Table.Cell>{jobAd.workTime.name}</Table.Cell>
              <Table.Cell>{jobAd.workPlace.name}</Table.Cell>
              <Table.Cell>
                {(
                  (new Date(jobAd.lastDate).getTime() -
                    new Date(Date.now()).getTime()) /
                  86400000
                )
                  .toString()
                  .split(".", 1)}{" "}
                gün
              </Table.Cell>
              <Table.Cell>
                <Button as={Link} to={`/myCandidate`}
                    content="Başvuranları Gör"
                    icon="right arrow"
                    labelPosition="right"
                  />
              </Table.Cell>

              
            </Table.Row>
          ))}
        </Table.Body>
      </Table>   
     
     

    </div>
  )
}
