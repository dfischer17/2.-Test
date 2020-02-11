package at.htlgkr.ticketingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class TicketAdapter extends BaseAdapter {
    private List<Ticket> ticketList;
    private int layoutId;
    private LayoutInflater inflater;
    private Context context;

    public TicketAdapter(Context context, List<Ticket> ticketList, int layoutId) {
        this.ticketList = ticketList;
        this.layoutId = layoutId;
        this.inflater = inflater;
        this.inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // implement this
        return ticketList.size();
    }

    @Override
    public Object getItem(int position) {
        // implement this
        return ticketList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // implement this
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // implement this
        Ticket ticket = ticketList.get(position);
        View listItem = (convertView == null) ?
                inflater.inflate(this.layoutId, null) : convertView;
        ((TextView) listItem.findViewById(R.id.et_title))
                .setText(ticket.getTitle());
        ((TextView) listItem.findViewById(R.id.et_description))
                .setText(ticket.getDescription());
        ((TextView) listItem.findViewById(R.id.et_start))
                .setText(ticket.getStart().format(DateTimeFormatter.ofPattern(Configuration.DATE_TIME_FORMAT)));
        ((TextView) listItem.findViewById(R.id.et_end))
                .setText(ticket.getEnd().format(DateTimeFormatter.ofPattern(Configuration.DATE_TIME_FORMAT)));
        return listItem;
    }
}
